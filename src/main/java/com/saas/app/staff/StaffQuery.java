package com.saas.app.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.staff.QueryCustomer;
import com.saas.auth.bo.staff.QueryStaff;
import com.saas.goods.bo.staff.QueryGoods;
import com.saas.goods.bo.staff.QueryGoodsOrder;
import com.saas.goods.bo.staff.QueryGoodsService;
import com.saas.payment.bo.QueryPayment;
import com.saas.training.bo.staff.QueryTraining;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Query")
public class StaffQuery {
	
	@Autowired
	private QueryStaff staff;
	
	@Autowired
	private QueryCustomer customer;
	
	@Autowired
	private QueryPayment payment;
	
	@Autowired
	private QueryGoods goods;
	
	@Autowired
	private QueryGoodsOrder goodsOrder;
	
	@Autowired
	private QueryGoodsService goodsService;

	@Autowired
	private QueryTraining training;

	@Field("staff")
	public QueryStaff getStaff() {
		return staff;
	}

	@Field("customer")
	public QueryCustomer getCustomer() {
		return customer;
	}

	@Field("payment")
	public QueryPayment getPayment() {
		return payment;
	}

	@Field("goods")
	public QueryGoods getGoods() {
		return goods;
	}

	@Field("goodsOrder")
	public QueryGoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	@Field("goodsService")
	public QueryGoodsService getGoodsService() {
		return goodsService;
	}

	@Field("training")
	public QueryTraining getTraining() {
		return training;
	}
}
