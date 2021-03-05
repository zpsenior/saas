package com.saas.app.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.staff.MutationCustomer;
import com.saas.auth.bo.staff.MutationStaff;
import com.saas.goods.bo.staff.MutationGoodsOrder;
import com.saas.goods.bo.staff.MutationGoods;
import com.saas.training.bo.staff.MutationCourse;
import com.saas.training.bo.staff.MutationTeacher;
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
	private MutationGoods goods;
	
	@Autowired
	private MutationGoodsOrder goodsOrder;
	
	@Autowired
	private MutationTraining training;
	
	@Autowired
	private MutationTeacher teacher;
	
	@Autowired
	private MutationCourse course;

	@Field("staff")
	public MutationStaff getStaff() {
		return staff;
	}

	@Field("customer")
	public MutationCustomer getCustomer() {
		return customer;
	}

	@Field("goods")
	public MutationGoods getGoods() {
		return goods;
	}

	@Field("goodsOrder")
	public MutationGoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	@Field("training")
	public MutationTraining getTraining() {
		return training;
	}

	@Field("teacher")
	public MutationTeacher getTeacher() {
		return teacher;
	}

	@Field("course")
	public MutationCourse getCourse() {
		return course;
	}
}
