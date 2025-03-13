package org.example.library.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.library.entity.Loan;

@Getter
@AllArgsConstructor
public class LoanResponseDto {

    private Long bookId;
    private String bookTitle;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime loanDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime returnDate;

    public static LoanResponseDto from(Loan loan) {
        return new LoanResponseDto(loan.getBook().getId(), loan.getBook().getTitle(),
            LocalDateTime.now(), loan.getReturnDate());
    }
}
