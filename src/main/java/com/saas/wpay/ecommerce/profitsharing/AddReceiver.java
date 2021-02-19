package com.saas.wpay.ecommerce.profitsharing;

import com.saas.wpay.WPayRequest;
import com.saas.wpay.ecommerce.po.ReceiverType;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class AddReceiver extends WPayRequest {
	
	public enum RelationType{
		SUPPLIER, //供应商
		DISTRIBUTOR, //分销商
		SERVICE_PROVIDER, //服务商
		PLATFORM, //平台
		OTHERS, //其他
	}
	
	public AddReceiver() {
		super("ecommerce/profitsharing/receivers/add", POST);
	}

	private String appid;
	
	private ReceiverType type;
	
	private String account;
	
	private String name;
	
	private String encrypted_name;
	
	private RelationType relation_type;
}
