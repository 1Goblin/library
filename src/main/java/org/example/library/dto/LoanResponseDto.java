package org.example.library.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoanResponseDto {

    private Long bookId;
    private String bookTitle;
    private LocalDate loanDate;
    private LocalDate returnDate;
}
