package com.sysbean.test.service;

import org.springframework.stereotype.Service;

import com.sysbean.test.dto.BookViewDTO;
import com.sysbean.test.model.BookAggrigator;
import com.sysbean.test.repository.BookAggrigatorRepository;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class BookAggrigatorServiceImpl implements BookAggrigatorService {
	
	private final BookAggrigatorRepository bookAggrigatorRepository;
	
	public BookAggrigatorServiceImpl(BookAggrigatorRepository bookAggrigatorRepository) {
		this.bookAggrigatorRepository = bookAggrigatorRepository;
	}

	@Override
	public BookViewDTO createBookAggrigator(BookViewDTO bookAggrigator) {
		BookAggrigator aggrigator = new BookAggrigator(bookAggrigator.getBookName() , bookAggrigator.getBookDescription(), bookAggrigator.getAuthorName());
		
		log.info("Entity stored {}, with ID: {}", aggrigator, aggrigator.getAggrigatorId());
		return convertIntoDTO(bookAggrigatorRepository.save(aggrigator)) ;
		
	}

	private BookViewDTO convertIntoDTO(BookAggrigator books) {
		if(books == null) {
			return null;
		}
		return BookViewDTO.builder()
				.bookName(books.getBookName())
				.bookDescription(books.getBookDescription())
				.authorName(books.getAuthorName())
				.build();
	}

}
