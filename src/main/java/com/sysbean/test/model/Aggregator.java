package com.sysbean.test.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "aggregates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aggregator {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long aggrigatorId;
	public String keySchema;

	public String valueSchema;

	public String materialization;
	
	
	
	public Aggregator(String keySchema, String valueSchema, String materialization) {
		this.keySchema = keySchema;
		this.valueSchema = valueSchema;
		this.materialization = materialization;
	}

	@Override
	public String toString() {
		return "Aggregator [aggrigatorId=" + aggrigatorId + ", keySchema=" + keySchema + ", valueSchema=" + valueSchema
				+ ", materialization=" + materialization + "]";
	}
	
}
