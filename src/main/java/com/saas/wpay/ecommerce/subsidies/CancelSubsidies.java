package com.saas.wpay.ecommerce.subsidies;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class CancelSubsidies extends WPayRequest {

	public CancelSubsidies() {
		super("ecommerce/subsidies/cancel", POST);
	}
	
	private String sub_mchid;
	
	private String transaction_id;
	
	private String description;

}
