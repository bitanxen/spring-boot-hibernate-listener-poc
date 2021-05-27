package com.sysbean.test.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "tb_book_aggrigator")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookAggrigator {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long aggrigatorId;
    private String bookName;
    private String bookDescription;
    private String authorName;
    
    
    public BookAggrigator(String bookName, String bookDescription, String authorName) {
    	this.bookName = bookName;
    	this.bookDescription = bookDescription;
    	this.authorName = authorName;
    }
}


