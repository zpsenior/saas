package com.saas.training.bo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.pub.BOBase;
import com.saas.training.dao.DAOCourse;
import com.saas.training.dao.DAOCourseItem;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component("Customer.MutationCourse")
public class MutationCourse extends BOBase {
	
	@Autowired
	private DAOCourse course;
	
	@Autowired
	private DAOCourseItem courseItem;

}
