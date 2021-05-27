package com.sysbean.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sysbean.test.model.Aggregator;

public interface AggregatorRepository extends JpaRepository<Aggregator, Long> {

}
