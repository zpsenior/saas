package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.ReceiverType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class DelReceiver extends WPayRequest {
	
	public DelReceiver() {
		super("ecommerce/profitsharing/receivers/delete", POST);
	}

	private String appid;
	
	private ReceiverType type;
	
	private String account;
}
