package com.sysbean.test.controller;

import com.sysbean.test.dto.BookDTO;
import com.sysbean.test.dto.CreatedBookDTO;
import com.sysbean.test.service.BookService;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book")
public class BookController {

	@Qualifier("dbBookService")
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/")
    public ResponseEntity<BookDTO> saveBook(@RequestBody CreatedBookDTO createdBook) {
        return ResponseEntity.ok(bookService.saveBook(createdBook));
    }
}
