package org.example.library.controller;


import lombok.RequiredArgsConstructor;
import org.example.library.dto.LoanResponseDto;
import org.example.library.service.LoanService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class LoanController {

    private final LoanService loanService;

    @PostMapping("/borrow/{memberId}/{bookId}")
    public ResponseEntity<LoanResponseDto> borrowBook(@PathVariable Long memberId,
        @PathVariable Long bookId) {
        LoanResponseDto loanResponseDto = loanService.borrowBook(memberId, bookId);
        return ResponseEntity.ok(loanResponseDto);
    }

    @PostMapping("/return/{bookId}")
    public ResponseEntity<LoanResponseDto> returnBook(@PathVariable Long memberId,
        @PathVariable Long bookId) {
        LoanResponseDto loanResponseDto = loanService.returnBook(memberId, bookId);
        return ResponseEntity.ok(loanResponseDto);
    }
}