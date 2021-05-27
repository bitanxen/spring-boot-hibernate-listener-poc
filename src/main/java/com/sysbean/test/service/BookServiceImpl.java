package com.sysbean.test.service;

import com.sysbean.test.dto.AuthorDTO;
import com.sysbean.test.dto.BookDTO;
import com.sysbean.test.dto.BookViewDTO;
import com.sysbean.test.dto.CreatedBookDTO;
import com.sysbean.test.model.Author;
import com.sysbean.test.model.Book;
import com.sysbean.test.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("dbBookService")
@Slf4j
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookDTO saveBook(CreatedBookDTO createdBook) {
        Book book = new Book(createdBook.getBookName(), createdBook.getBookDescription(), createdBook.getAuthors());
        return convertIntoDTO(bookRepository.save(book));
    }

    @Override
    public BookDTO convertIntoDTO(Book book) {
        if(book == null)
            return null;

        return BookDTO.builder()
                .bookId(book.getBookId())
                .bookName(book.getBookName())
                .bookDescription(book.getBookDescription())
                .authors(book.getAuthors().stream().map(this::convertIntoAuthorDTO).collect(Collectors.toList()))
                .build();
    }

    private AuthorDTO convertIntoAuthorDTO(Author author) {
        if(author == null) {
            return null;
        }

        return AuthorDTO.builder()
                .authorId(author.getAuthorId())
                .authorName(author.getAuthorName())
                .build();
    }
}
