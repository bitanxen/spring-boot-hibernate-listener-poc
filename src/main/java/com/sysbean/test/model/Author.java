package com.sysbean.test.model;

import lombok.*;

import javax.persistence.*;

@Entity(name = "tb_author")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long authorId;

    private String authorName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "book_id", nullable = false, foreignKey = @ForeignKey(name = "fk_author_book"))
    private Book book;

    public Author(Book book, String authorName) {
        this.book = book;
        this.authorName = authorName;
    }

    @Override
    public String toString() {
        return "Author{" +
                "authorId=" + authorId +
                ", authorName='" + authorName + '\'' +
                ", book=" + book.getBookId() +
                '}';
    }
}
