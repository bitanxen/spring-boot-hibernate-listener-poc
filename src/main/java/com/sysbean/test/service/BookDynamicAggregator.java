package com.sysbean.test.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.sysbean.test.model.Book;

@Component
public class BookDynamicAggregator implements DynamicAggregator {

	
    private final AggregatorService aggregatorService;
    
    public BookDynamicAggregator(AggregatorService aggregatorService) {
        this.aggregatorService = aggregatorService;
	}
	
    
	@Override
	public void doAggregation(Object entity, String id, String idPropName) {
		Book book = (Book) entity;
		System.out.println(book.getBookName());
       // aggregatorService.createAggregator(book.getBookId(),  id,  idPropName).start();
	}

}
