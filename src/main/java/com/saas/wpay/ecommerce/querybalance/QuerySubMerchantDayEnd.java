package com.saas.wpay.ecommerce.querybalance;

import java.util.Date;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class QuerySubMerchantDayEnd extends WPayRequest {

	public QuerySubMerchantDayEnd() {
		super("ecommerce/fund/enddaybalance/{sub_mchid}", GET);
	}
	
	private String sub_mchid;
	
	private Date date;

}
