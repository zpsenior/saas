package com.saas.app.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.staff.MutationCustomer;
import com.saas.auth.bo.staff.MutationStaff;
import com.saas.goods.bo.staff.MutationGoodsOrder;
import com.saas.goods.bo.staff.MutationGoodsService;
import com.saas.goods.bo.staff.MutationGoods;
import com.saas.payment.bo.MutationPayment;
import com.saas.training.bo.staff.MutationTraining;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Mutation")
public class StaffMutation {
	
	@Autowired
	private MutationStaff staff;
	
	@Autowired
	private MutationCustomer customer;
	
	@Autowired
	private MutationPayment payment;
	
	@Autowired
	private MutationGoods goods;
	
	@Autowired
	private MutationGoodsOrder goodsOrder;
	
	@Autowired
	private MutationGoodsService goodsService;
	
	@Autowired
	private MutationTraining training;

	@Field("staff")
	public MutationStaff getStaff() {
		return staff;
	}

	@Field("customer")
	public MutationCustomer getCustomer() {
		return customer;
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

	@Field("training")
	public MutationTraining getTraining() {
		return training;
	}
}
