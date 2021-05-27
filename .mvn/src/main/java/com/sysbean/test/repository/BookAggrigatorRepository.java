package com.sysbean.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sysbean.test.model.BookAggrigator;

public interface BookAggrigatorRepository extends JpaRepository<BookAggrigator, Long> {

}
