package com.sysbean.test.config;

import com.sysbean.test.model.Book;
import com.sysbean.test.service.AggregatorService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@Slf4j
public class HibernatePostListener implements PostCommitInsertEventListener {

    private final EntityManagerFactory entityManagerFactory;
    private final AggregatorService aggregatorService;

    public HibernatePostListener(EntityManagerFactory entityManagerFactory, AggregatorService aggregatorService) {
        this.entityManagerFactory = entityManagerFactory;
        this.aggregatorService = aggregatorService;
    }

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
    }

    @Override
    public void onPostInsertCommitFailed(PostInsertEvent postInsertEvent) {
    }

    @Override
    public boolean requiresPostCommitHanding(EntityPersister entityPersister) {
        return false;
    }

    @Override
    public void onPostInsert(PostInsertEvent postInsertEvent) {
        String id = postInsertEvent.getId().toString();
        Object entity = postInsertEvent.getEntity();

        if(entity instanceof Book) {
            Book book = (Book) entity;
            aggregatorService.createAggregator(book.getBookId()).start();
        }

        log.info("Entity stored {}, with ID: {}", entity, id);
    }
}
