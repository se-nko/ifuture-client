package com.ifuture.client.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AmountRequest {

	Integer id;
	Long value;

}
