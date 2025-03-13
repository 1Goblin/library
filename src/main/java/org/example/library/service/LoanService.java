package org.example.library.service;


import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.example.library.dto.LoanResponseDto;
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

    public LoanResponseDto borrowBook(Long memberId, Long bookId) {
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        if (!book.isAvailable()) {      //@Getter는 boolean타입이면 isAvailable로 가져올 수 있음
            throw new CustomException(ErrorCode.BOOK_ALREADY_BORROWED);
        }

        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Loan loan = Loan.builder()
            .member(member)
            .book(book)
            .loanDate(LocalDate.now())
            .returnDate(null)
            .build();

        book.setAvailable(false);
        Loan saveLoan = loanRepository.save(loan);
        return LoanResponseDto.from(saveLoan);
    }

    public LoanResponseDto returnBook(Long memberId, Long bookId) {
        Loan loan = loanRepository.findByMemberIdAndBookIdAndReturnDateIsNull(memberId, bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        loan.setReturnDate(LocalDate.now());
        loan.getBook().setAvailable(true);

        Loan saveLoan = loanRepository.save(loan);

        return LoanResponseDto.from(saveLoan);
    }
}