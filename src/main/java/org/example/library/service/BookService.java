package org.example.library.service;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.library.dto.BookRequestDto;
import org.example.library.dto.BookResponseDto;
import org.example.library.entity.Book;
import org.example.library.exception.CustomException;
import org.example.library.exception.ErrorCode;
import org.example.library.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {

    private final BookRepository bookRepository;

    public BookResponseDto addBook(BookRequestDto bookRequestDto) {
        Book book = Book.builder()
            .title(bookRequestDto.getTitle())
            .author(bookRequestDto.getAuthor())
            .available(true) // 기본값 설정
            .build();

        Book saveBook = bookRepository.save(book);

        return BookResponseDto.from(saveBook);

    }

    public BookResponseDto getBookById(Long id) {

        Book book = bookRepository.findById(id)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        return BookResponseDto.from(book);
    }

    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream().map(BookResponseDto::from).toList();
    }

    public void deleteBook(Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        bookRepository.delete(book); //조회한 엔티티를 삭제
    }
}