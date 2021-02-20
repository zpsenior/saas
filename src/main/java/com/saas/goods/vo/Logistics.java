package com.saas.goods.vo;

import java.util.Date;

import com.saas.auth.vo.Gender;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="物流信息表", incr="logisticsId")
public class Logistics {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;
	
	@Field(isKey=true, desc="物流单号")
	private long logisticsId;

	@Field(desc="订单ID")
	private long orderId;

	@Field(desc="客户ID")
	private long customerId;

	@Field(desc="快递公司")
	private String expressId;
	
	@Field(desc="快递单号", len=50)
	private long expressNo;

	@Field(desc="发出日期")
	private Date sendOutDate;

	@Field(desc="收件人")
	private String recipient;
	
	@Field(desc="收件人性别")
	private Gender gender;

	@Field(desc="收件人联系电话")
	private String mobile;

	@Field(desc="省")
	private String province;

	@Field(desc="地市")
	private String city;

	@Field(desc="区县")
	private String county;

	@Field(desc="详细地址", len=100)
	private String detail;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}
