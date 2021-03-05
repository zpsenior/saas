package com.saas.profitshare.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="分润流水表", incr="shareId")
public class ProfitShare {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="流水ID")
	private long shareId;

	@Field(desc="订单ID")
	private long orderId;

	@Field(desc="订单明细ID")
	private long orderItemId;

	@Field(desc="商品ID")
	private long goodsId;

	@Field(desc="受益人ID")
	private long ownerId;

	@Field(desc="推荐方式")
	private ShareType shareType;

	@Field(desc="分润金额")
	private long amount;

	@Field(desc="创建日期")
	private Date createDate;

}
