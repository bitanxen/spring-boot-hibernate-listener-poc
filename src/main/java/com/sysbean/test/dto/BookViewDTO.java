package com.sysbean.test.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookViewDTO {
    private Long bookId;
    private String bookName;
    private String bookDescription;
    private Long authorId;
    private String authorName;
}
