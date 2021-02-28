package com.saas.goods.vo;

import java.util.Date;
import java.util.List;

import com.saas.auth.vo.Gender;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品订单表", incr="orderId")
public class Order {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="订单ID")
	private long orderId;

	@Field(desc="客户ID")
	private long customerId;

	@Field(desc="订单总金额")
	private long totalAmount;

	@Field(desc="邮费")
	private int postage;

	@Field(desc="订单状态")
	private OrderStatus status;

	@Field(desc="支付类型")
	private PayType payType;

	@Field(desc="收件人")
	private String recipient;
	
	@Field(desc="收件人性别")
	private Gender gender;

	@Field(desc="收件人联系电话")
	private String mobileno;

	@Field(desc="省")
	private String province;

	@Field(desc="地市", len=50)
	private String city;

	@Field(desc="区县", len=50)
	private String county;

	@Field(desc="详细地址", len=100)
	private String detail;
	
	@Field(desc="支付日期")
	private Date payDate;
	
	@Field(desc="发货日期")
	private Date sendDate;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

	@Join(request = "QueryGoodsOrder.queryOrderItemList", params = { "tenantId", "orderId" })
	private List<OrderItem> items;
	
	public Order() {}
	
	public Order(PostAddress address) {
		this.recipient = address.getRecipient();
		this.gender = address.getGender();
		this.mobileno = address.getMobileno();
		this.province = address.getProvince();
		this.city = address.getCity();
		this.county = address.getCounty();
		this.detail = address.getDetail();
	}

}
