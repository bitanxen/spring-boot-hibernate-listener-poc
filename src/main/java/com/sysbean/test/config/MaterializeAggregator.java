package com.sysbean.test.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sysbean.test.service.DynamicAggregator;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface MaterializeAggregator {
	public String name();
	public Class<? extends DynamicAggregator> aggregator();
}
