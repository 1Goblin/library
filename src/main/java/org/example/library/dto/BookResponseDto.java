package org.example.library.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.example.library.entity.Book;

@Getter
@AllArgsConstructor
public class BookResponseDto {

    private Long id;  // 응답에서만 필요한 값
    private String title;
    private String author;


    public static BookResponseDto from(Book book) {
        return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor());
    }
}