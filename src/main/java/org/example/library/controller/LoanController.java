package org.example.library.controller;


import java.util.List;
import lombok.RequiredArgsConstructor;
import org.example.library.dto.LoanResponseDto;
import org.example.library.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/loan")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    //책 빌리기
    @PostMapping("/borrow/{memberId}/{bookId}")
    public ResponseEntity<LoanResponseDto> borrowBook(@PathVariable Long memberId,
        @PathVariable Long bookId) {
        LoanResponseDto loanResponseDto = loanService.borrowBook(memberId, bookId);
        return ResponseEntity.ok(loanResponseDto);
    }

    //책 반납하기
    @PostMapping("/return/{memberId}/{bookId}")
    public ResponseEntity<LoanResponseDto> returnBook(@PathVariable Long memberId,
        @PathVariable Long bookId) {
        LoanResponseDto loanResponseDto = loanService.returnBook(memberId, bookId);
        return ResponseEntity.ok(loanResponseDto);
    }


    //빌린책 조회하기
    @GetMapping("/member/{memberId}/active")
    public ResponseEntity<List<LoanResponseDto>> getActiveLoans(@PathVariable Long memberId) {

        List<LoanResponseDto> loanResponseDtos = loanService.getLoans(memberId);

        return ResponseEntity.ok(loanResponseDtos);
    }


}