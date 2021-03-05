package com.saas.app.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.bo.customer.MutationCustomer;
import com.saas.goods.bo.customer.MutationGoodsOrder;
import com.saas.goods.bo.customer.MutationPostAddress;
import com.saas.training.bo.customer.MutationCourse;
import com.saas.training.bo.customer.MutationTeacher;
import com.saas.training.bo.customer.MutationTraining;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

@Component
@Type("Mutation")
public class CustomerMutation {
	
	@Autowired
	private MutationCustomer customer;
	
	@Autowired
	private MutationGoodsOrder goodsOrder;
	
	@Autowired
	private MutationPostAddress postAddress;
	
	@Autowired
	private MutationTraining training;
	
	@Autowired
	private MutationTeacher teacher;
	
	@Autowired
	private MutationCourse course;

	@Field("customer")
	public MutationCustomer getCustomer() {
		return customer;
	}

	@Field("goodsOrder")
	public MutationGoodsOrder getGoodsOrder() {
		return goodsOrder;
	}

	@Field("postAddress")
	public MutationPostAddress getPostAddress() {
		return postAddress;
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
