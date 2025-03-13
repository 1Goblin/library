package org.example.library.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 회원 관련 예외
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    USER_ALREADY_EXISTS(HttpStatus.CONFLICT, "이미 존재하는 사용자입니다."),

    //도서 관련 예외
    BOOK_NOT_FOUND(HttpStatus.NOT_FOUND, "해당 도서를 찾을 수 없습니다."),
    BOOK_ALREADY_BORROWED(HttpStatus.BAD_REQUEST, "이미 대출된 도서입니다."),
    BOOK_NOT_BORROWED(HttpStatus.BAD_REQUEST, "대출되지 않은 도서입니다."),

    //인증 관련 예외
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "인증되지 않은 사용자입니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "접근 권한이 없습니다.");


    private final HttpStatus httpStatus;
    private final String errorMessage;


}
