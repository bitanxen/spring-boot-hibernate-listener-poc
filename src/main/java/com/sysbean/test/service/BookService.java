package com.sysbean.test.service;

import com.sysbean.test.dto.BookDTO;
import com.sysbean.test.dto.CreatedBookDTO;

public interface BookService {
    BookDTO saveBook(CreatedBookDTO createdBook);
}
