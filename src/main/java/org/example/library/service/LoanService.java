package org.example.library.service;


import java.time.LocalDate;
import java.util.List;
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

    //책 빌리기
    public LoanResponseDto borrowBook(Long memberId, Long bookId) {

        //책 존재 확인
        Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));
        if (!book.isAvailable()) {      //@Getter는 boolean타입이면 isAvailable로 가져올 수 있음
            throw new CustomException(ErrorCode.BOOK_ALREADY_BORROWED);
        }

        //회원 존재 확인
        Member member = memberRepository.findById(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        //대출 객체 생성
        Loan loan = Loan.builder()
            .member(member)
            .book(book)
            .loanDate(LocalDate.now())
            .returnDate(null)
            .build();

        //책 대출 불가능 설정
        book.setAvailable(false);

        //대출 객체 생성
        Loan saveLoan = loanRepository.save(loan);
        return LoanResponseDto.from(saveLoan);
    }

    //책 반납하기
    public LoanResponseDto returnBook(Long memberId, Long bookId) {
        Loan loan = loanRepository.findByMemberIdAndBookIdAndReturnDateIsNull(memberId, bookId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        loan.setReturnDate(LocalDate.now());
        loan.getBook().setAvailable(true);

        Loan saveLoan = loanRepository.save(loan);

        return LoanResponseDto.from(saveLoan);
    }

    //사용자가 빌민 책 조회
    public List<LoanResponseDto> getLoans(Long memberId) {
        List<Loan> loans = loanRepository.findByMemberIdAndReturnDateIsNull(memberId)
            .orElseThrow(() -> new CustomException(ErrorCode.BOOK_NOT_FOUND));

        return loans.stream().map(LoanResponseDto::from).toList();
    }
}