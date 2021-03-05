package com.saas.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.customer.QueryCustomer;
import com.saas.goods.bo.customer.QueryGoods;
import com.saas.goods.bo.customer.QueryGoodsOrder;
import com.saas.goods.bo.customer.QueryPostAddress;
import com.saas.profitshare.bo.customer.QueryProfitShare;
import com.saas.training.bo.customer.QueryTraining;
import com.saas.training.bo.customer.QueryTeacher;
import com.saas.training.bo.customer.QueryCourse;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;


@Component
@Type("Query")
public class CustomerQuery {
	
	@Autowired
	private QueryCustomer customer;
	
	@Autowired
	private QueryGoods goods;
	
	@Autowired
	private QueryGoodsOrder goodsOrder;
	
	@Autowired
	private QueryProfitShare profitShare;

	@Autowired
	private QueryPostAddress postAddress;

	@Autowired
	private QueryTraining training;

	@Autowired
	private QueryTeacher teacher;

	@Autowired
	private QueryCourse course;

	@Field("customer")
	public QueryCustomer getCustomer() {
		return customer;
	}

	@Field("goods")
	public QueryGoods getGoods() {
		return goods;
	}
	
	@Field("profitShare")
	public QueryProfitShare getProfitShare() {
		return profitShare;
	}

	@Field("goodsOrder")
	public QueryGoodsOrder getGoodsOrder() {
		return goodsOrder;
	}
	
	@Field("postAddress")
	public QueryPostAddress getPostAddress() {
		return postAddress;
	}
	
	@Field("training")
	public QueryTraining getTraining() {
		return training;
	}

	@Field("teacher")
	public QueryTeacher getTeacher() {
		return teacher;
	}

	@Field("course")
	public QueryCourse getCourse() {
		return course;
	}
	
	
}
