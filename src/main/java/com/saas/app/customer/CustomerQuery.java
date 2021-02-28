package com.saas.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.customer.QueryCustomer;
import com.saas.goods.bo.customer.QueryGoods;
import com.saas.goods.bo.customer.QueryGoodsOrder;
import com.saas.goods.bo.customer.QueryGoodsService;
import com.saas.goods.bo.customer.QueryPostAddress;
import com.saas.payment.bo.QueryPayment;
import com.saas.training.bo.staff.QueryTraining;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Query")
public class CustomerQuery {
	
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
	private QueryPostAddress postAddress;

	@Autowired
	private QueryTraining training;

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
	
	@Field("postAddress")
	public QueryPostAddress getPostAddress() {
		return postAddress;
	}
	
	@Field("training")
	public QueryTraining getTraining() {
		return training;
	}
}
