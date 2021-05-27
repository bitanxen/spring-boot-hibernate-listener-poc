package com.sysbean.test.repository;

import com.sysbean.test.dto.BookViewDTO;
import com.sysbean.test.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(" SELECT new com.sysbean.test.dto.BookViewDTO(B.bookId, B.bookName, B.bookDescription, A.authorId, A.authorName) " +
            "  FROM tb_book B " +
            "  LEFT OUTER JOIN tb_author A " +
            "    ON A.book.bookId = B.bookId " +
            " WHERE B.bookId = :bookId")
    List<BookViewDTO> getBookViewData(Long bookId);
}
