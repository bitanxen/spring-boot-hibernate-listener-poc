package com.sysbean.test.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AggregatorDTO {
   private Long aggrigatorId;
   private String keySchema;
   private String valueSchema;
   private String materilazation;

}
