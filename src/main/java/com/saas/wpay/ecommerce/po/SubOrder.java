package com.saas.wpay.ecommerce.po;

import lombok.Data;

@Data
public class SubOrder{
	
	@Data
	public class Amount{
		
		private long total_amount;
		private String currency;
	}
	
	
	
	private String mchid;
	private String attch;
	private Amount amount;
	private String out_trade_no;
	private String sub_mchid;
	private String description;
	private SettleInfo settle_info;
}
