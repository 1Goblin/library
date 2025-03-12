package org.example.library.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanRequestDto {

    private Long memberId;
    private Long bookId;
}