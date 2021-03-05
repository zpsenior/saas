package com.saas.app.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.staff.QueryCustomer;
import com.saas.auth.bo.staff.QueryStaff;
import com.saas.goods.bo.staff.QueryGoods;
import com.saas.goods.bo.staff.QueryGoodsOrder;
import com.saas.training.bo.staff.QueryCourse;
import com.saas.training.bo.staff.QueryTeacher;
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
	private QueryGoods goods;
	
	@Autowired
	private QueryGoodsOrder goodsOrder;

	@Autowired
	private QueryTraining training;

	@Autowired
	private QueryTeacher teacher;

	@Autowired
	private QueryCourse course;

	@Field("staff")
	public QueryStaff getStaff() {
		return staff;
	}

	@Field("customer")
	public QueryCustomer getCustomer() {
		return customer;
	}

	@Field("goods")
	public QueryGoods getGoods() {
		return goods;
	}

	@Field("goodsOrder")
	public QueryGoodsOrder getGoodsOrder() {
		return goodsOrder;
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
