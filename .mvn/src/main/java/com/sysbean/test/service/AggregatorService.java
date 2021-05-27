package com.sysbean.test.service;

import com.sysbean.test.dto.AggregatorDTO;


public interface AggregatorService {
	void createAggregator(Long bookId, String id, String idPropName);

	AggregatorDTO saveAggregator(String id, String idPropName, String materialisation);
}
