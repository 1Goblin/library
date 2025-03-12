package org.example.library.service;


import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.example.library.entity.Book;
import org.example.library.entity.Loan;
import org.example.library.entity.Member;
import org.example.library.exception.CustomException;
import org.example.library.exception.ErrorCode;
import org.example.library.repository.BookRepository;
import org.example.library.repository.LoanRepository;
import org.example.library.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class LoanService {

    private final LoanRepository loanRepository;
    private final BookRepository bookRepository;
    private final MemberRepository memberRepository;

    public Loan borrowBook(Long memberId, Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        if (!book.isAvailable()) {
            throw new CustomException(ErrorCode.BOOK_ALREADY_BORROWED);
        }

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Loan loan = Loan.builder()
            .member(member)
            .book(book)
            .loanDate(LocalDate.now())
            .build();

        book.setAvailable(false);
        return loanRepository.save(loan);
    }

    public void returnBook(Long bookId) {
        Loan loan = loanRepository.findByBookIdAndReturnDateIsNull(bookId)
            .orElseThrow(() -> new IllegalArgumentException("대출 정보를 찾을 수 없습니다."));

        loan.setReturnDate(LocalDate.now());
        loan.getBook().setAvailable(true);
    }
}