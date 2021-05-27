package com.sysbean.test.config;

import com.sysbean.test.model.Book;
import com.sysbean.test.service.AggregatorService;
import com.sysbean.test.service.DynamicAggregator;

import lombok.extern.slf4j.Slf4j;

import org.hibernate.action.spi.BeforeTransactionCompletionProcess;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventSource;
import org.hibernate.event.spi.EventType;
import org.hibernate.event.spi.PostCommitInsertEventListener;
import org.hibernate.event.spi.PostInsertEvent;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.persister.entity.EntityPersister;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManagerFactory;

@Component
@Slf4j
public class HibernatePostListener implements PostCommitInsertEventListener {

    private final EntityManagerFactory entityManagerFactory;
    private final ApplicationContext context;


    public HibernatePostListener(EntityManagerFactory entityManagerFactory, ApplicationContext context) {
        this.entityManagerFactory = entityManagerFactory;
        this.context = context;
        log.info("Registerning Listener...");
    }

    @PostConstruct
    private void init() {
        SessionFactoryImpl sessionFactory = entityManagerFactory.unwrap(SessionFactoryImpl.class);
        EventListenerRegistry registry = sessionFactory.getServiceRegistry().getService(EventListenerRegistry.class);
        registry.getEventListenerGroup(EventType.POST_INSERT).appendListener(this);
        log.info("Listener registered...");
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
        String idPropName = postInsertEvent.getPersister().getIdentifierPropertyName();
        EventSource session = postInsertEvent.getSession(); 
        
        session.getActionQueue().registerProcess(new BeforeTransactionCompletionProcess() {

			@Override
			public void doBeforeTransactionCompletion(SessionImplementor session) {
				if (!session.getTransactionCoordinator().isActive()) {
                    return;
                }
				
				MaterializeAggregator aggregator = entity.getClass().getAnnotation(MaterializeAggregator.class);
		        
		        if(aggregator != null) {
		        	
		        	Class<? extends DynamicAggregator> entityAggregator = aggregator.aggregator();
		        	
		        	DynamicAggregator dynamicAggregator = context.getBean(entityAggregator);
		        	dynamicAggregator.doAggregation(entity, id, idPropName);
		        	
		            log.info("Entity stored {}, with ID: {}, aggregation: {}", entity, id, aggregator.name());
		        }
				
			}
        	
        });
        
        
    }
    
    
}
