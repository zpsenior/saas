package com.saas.training.bo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.pub.BOBase;
import com.saas.training.dao.DAOTeacher;
import com.saas.training.dao.DAOTeacherCircle;
import com.saas.training.dao.DAOTeacherReview;
import com.saas.training.request.QueryTeacherCircleParam;
import com.saas.training.request.QueryTeacherParam;
import com.saas.training.request.QueryTeacherReviewParam;
import com.saas.training.vo.Teacher;
import com.saas.training.vo.TeacherCircle;
import com.saas.training.vo.TeacherReview;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.QueryTeacher")
public class QueryTeacher extends BOBase {
	
	@Autowired
	private DAOTeacher teacher;
	
	@Autowired
	private DAOTeacherReview teacherReview;
	
	@Autowired
	private DAOTeacherCircle teacherCircle;

	@Field("teachers")
	public List<Teacher> queryTeacherList(@Var("params") QueryTeacherParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		return teacher.queryTeacherList(params);
	}

	@Field("teacher")
	public Teacher getTeacher(@Var("staffId") long staffId)throws Exception{
		Teacher params = new Teacher();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setStaffId(staffId);
		return teacher.getTeacher(params);
	}

	@Field("teacherReviews")
	public List<TeacherReview> queryTeacherReviewList(@Var("staffId") long staffId)throws Exception{
		QueryTeacherReviewParam params = new QueryTeacherReviewParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setStaffId(staffId);
		return teacherReview.queryTeacherReviewList(params);
	}

	@Field("teacherCircles")
	public List<TeacherCircle> queryTeacherCircleList(@Var("staffId") long staffId)throws Exception{
		QueryTeacherCircleParam params = new QueryTeacherCircleParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setStaffId(staffId);
		return teacherCircle.queryTeacherCircleList(params);
	}
	
}
