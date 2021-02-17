package com.saas.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.QueryCustomer;
import com.saas.auth.bo.QueryPrivilege;
import com.saas.auth.bo.QueryStaff;
import com.saas.auth.bo.QueryTenant;
import com.saas.goods.bo.QueryGoods;
import com.saas.goods.bo.QueryGoodsOrder;
import com.saas.goods.bo.QueryGoodsService;
import com.saas.goods.bo.QueryPostAddress;
import com.saas.payment.bo.QueryPayment;
import com.saas.training.bo.QueryTraining;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Query")
public class StaffQuery {
	
	@Autowired
	private QueryStaff staff;
	
	@Autowired
	private QueryTenant tenant;
	
	@Autowired
	private QueryCustomer customer;
	
	@Autowired
	private QueryPrivilege privilege;
	
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

	@Field("staff")
	public QueryStaff getStaff() {
		return staff;
	}

	@Field("tenant")
	public QueryTenant getTenant() {
		return tenant;
	}

	@Field("customer")
	public QueryCustomer getCustomer() {
		return customer;
	}
	
	@Field("privilege")
	public QueryPrivilege getPrivilege() {
		return privilege;
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
