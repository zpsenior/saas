package com.saas.wpay.ecommerce.subsidies;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CreateSubsidies extends WPayRequest {

	public CreateSubsidies() {
		super("ecommerce/subsidies/create", POST);
	}
	
	private String sub_mchid;
	
	private String transaction_id;
	
	private int amount;
	
	private String description;
	
	private String refund_id;

}
