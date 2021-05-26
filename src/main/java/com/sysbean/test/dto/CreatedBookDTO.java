package com.sysbean.test.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CreatedBookDTO {
    private String bookName;
    private String bookDescription;
    private List<String> authors;
}
