package com.sysbean.test.service;

import com.sysbean.test.dto.BookViewDTO;
import com.sysbean.test.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class AggregatorServiceImpl implements AggregatorService {

    private final BookRepository bookRepository;

    public AggregatorServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Thread createAggregator(Long bookId) {
        Runnable runnable = () -> {
            try {
                Thread.sleep(1000);
            } catch (Exception e) {}
            List<BookViewDTO> books = bookRepository.getBookViewData(bookId);
            log.info("Aggregation ID {}: {}", bookId, books);
        };
        return new Thread(runnable);
    }
}
