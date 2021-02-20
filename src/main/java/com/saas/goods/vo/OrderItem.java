package com.saas.goods.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="商品订单明细表", incr="orderItemId")
public class OrderItem {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;
	
	@Field(isKey=true, desc="订单ID")
	private long orderId;

	@Field(isKey=true, desc="订单明细ID")
	private long orderItemId;

	@Field(desc="商品ID")
	private long goodsId;

	@Field(desc="商品序列号")
	private String serialNo;

	@Field(desc="商品数量")
	private int count;

	@Field(desc="商品金额")
	private long amount;
	
	@Join(bind = "getGoods", params = { "goodsId" })
	private Goods goods;

	@Field(desc="物流单号", len=50)
	private String logisticsId;

	@Join(bind = "getLogistics", params = { "tenantId", "orderId" })
	private Logistics logistics;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}
