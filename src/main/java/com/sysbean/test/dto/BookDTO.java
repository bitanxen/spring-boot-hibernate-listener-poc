package com.sysbean.test.dto;

import com.sysbean.test.model.Author;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class BookDTO {
    private Long bookId;
    private String bookName;
    private String bookDescription;
    private List<AuthorDTO> authors;
}
