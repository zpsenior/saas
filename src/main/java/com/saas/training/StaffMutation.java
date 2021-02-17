package com.saas.training;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.MutationCustomer;
import com.saas.auth.bo.MutationPrivilege;
import com.saas.auth.bo.MutationStaff;
import com.saas.auth.bo.MutationTenant;
import com.saas.goods.bo.MutationGoods;
import com.saas.goods.bo.MutationGoodsOrder;
import com.saas.goods.bo.MutationGoodsService;
import com.saas.goods.bo.MutationPostAddress;
import com.saas.payment.bo.MutationPayment;
import com.saas.training.bo.MutationTraining;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Mutation")
public class StaffMutation {
	
	@Autowired
	private MutationStaff staff;
	
	@Autowired
	private MutationTenant tenant;
	
	@Autowired
	private MutationCustomer customer;
	
	@Autowired
	private MutationPrivilege privilege;
	
	@Autowired
	private MutationPayment payment;
	
	@Autowired
	private MutationGoods goods;
	
	@Autowired
	private MutationGoodsOrder goodsOrder;
	
	@Autowired
	private MutationGoodsService goodsService;
	
	@Autowired
	private MutationPostAddress postAddress;
	
	@Autowired
	private MutationTraining training;

	@Field("staff")
	public MutationStaff getStaff() {
		return staff;
	}

	@Field("tenant")
	public MutationTenant getTenant() {
		return tenant;
	}

	@Field("customer")
	public MutationCustomer getCustomer() {
		return customer;
	}
	
	@Field("privilege")
	public MutationPrivilege getPrivilege() {
		return privilege;
	}

	@Field("payment")
	public MutationPayment getPayment() {
		return payment;
	}

	@Field("goods")
	public MutationGoods getGoods() {
		return goods;
	}

	@Field("goodsOrder")
	public MutationGoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	@Field("goodsService")
	public MutationGoodsService getGoodsService() {
		return goodsService;
	}

	@Field("postAddress")
	public MutationPostAddress getPostAddress() {
		return postAddress;
	}

	@Field("training")
	public MutationTraining getTraining() {
		return training;
	}
}
