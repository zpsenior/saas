package com.saas.training.bo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.pub.BOBase;
import com.saas.training.dao.DAOCourse;
import com.saas.training.dao.DAOCourseItem;
import com.saas.training.vo.Course;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.MutationCourse")
public class MutationCourse extends BOBase {
	
	@Autowired
	private DAOCourse course;
	
	@Autowired
	private DAOCourseItem courseItem;

	public boolean addCourse(@Var("description") String description)throws Exception {
		TenantStaffSession session = getStaffSession();
		Course params = new Course();
		params.setTenantId(session.getTenantId());
		params.setStaffId(session.getStaffId());
		course.addCourse(params);
		
		return true;
	}
}
