package com.sysbean.test.service;

import com.sysbean.test.dto.BookDTO;
import com.sysbean.test.dto.CreatedBookDTO;
import com.sysbean.test.model.Book;

public interface BookService {
    BookDTO saveBook(CreatedBookDTO createdBook);
    
    BookDTO convertIntoDTO(Book book);
}
