package com.sysbean.test.service;

import java.util.List;
import java.util.stream.Collectors;


import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sysbean.test.dto.AggregatorDTO;
import com.sysbean.test.dto.BookDTO;
import com.sysbean.test.dto.BookViewDTO;
import com.sysbean.test.model.Aggregator;
import com.sysbean.test.model.Book;
import com.sysbean.test.repository.AggregatorRepository;
import com.sysbean.test.repository.BookAggrigatorRepository;
import com.sysbean.test.repository.BookRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@Transactional
public class AggregatorServiceImpl implements AggregatorService {

	private final BookRepository bookRepository;
	private final AggregatorRepository aggregatorRepository;

	private final BookService bookService;
	private final BookAggrigatorService bookAggrigatorService;

	private final ObjectMapper objectMapper;

	public AggregatorServiceImpl(BookRepository bookRepository, BookService bookService,
			BookAggrigatorRepository bookAggrigatorRepository, BookAggrigatorService bookAggrigatorService,
			ObjectMapper objectMapper, AggregatorRepository aggregatorRepository) {
		this.bookRepository = bookRepository;
		this.bookService = bookService;
		this.bookAggrigatorService = bookAggrigatorService;
		this.objectMapper = objectMapper;
		this.aggregatorRepository = aggregatorRepository;
	}

	@Override
	public void createAggregator(Long bookId, String id, String idPropName) {

		Book existingBook = bookRepository.getOne(bookId);
		// System.out.println("Existing Book :" + existingBook);
		// Hibernate.initialize(existingBook.getBookId());

		BookDTO bookDTO = bookService.convertIntoDTO(existingBook);

		//List<BookViewDTO> books = bookRepository.getBookViewData(bookId);
		//log.info("Aggregation ID {}: {}", bookId, books);

		//books.stream().map(book -> bookAggrigatorService.createBookAggrigator(book)).collect(Collectors.toList());

		String bookStr;
		try {
			bookStr = objectMapper.writeValueAsString(bookDTO);
			AggregatorDTO aggr = saveAggregator(id, idPropName, bookStr);
			log.info("Aggregator saved: {}", aggr.getAggrigatorId());
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// for(BookViewDTO book : books) {
		// bookAggrigatorService.createBookAggrigator(book);
		// }

		// return convertIntoDTO(aggregatorRepository.save(aggregate));
	}

	@Override
	@Transactional
	public AggregatorDTO saveAggregator(String id, String idPropName, String materialisation) {
		Aggregator aggregate = new Aggregator("Key Schema", "Value Schema", materialisation);

		System.out.println("materilazition :" + materialisation);

		log.info("Aggregator data saved");

		return convertIntoDTO(aggregatorRepository.saveAndFlush(aggregate));

	}

	private AggregatorDTO convertIntoDTO(Aggregator aggregate) {
		if (aggregate == null) {
			return null;
		}
		return AggregatorDTO.builder().aggrigatorId(aggregate.getAggrigatorId()).keySchema(aggregate.getKeySchema())
				.valueSchema(aggregate.getValueSchema()).materilazation(aggregate.getMaterialization()).build();
	}

}
