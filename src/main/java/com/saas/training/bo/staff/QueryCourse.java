package com.saas.training.bo.staff;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.pub.BOBase;
import com.saas.training.dao.DAOCourse;
import com.saas.training.dao.DAOCourseItem;
import com.saas.training.request.QueryCourseParam;
import com.saas.training.vo.Course;
import com.saas.training.vo.CourseItem;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.QueryCourse")
public class QueryCourse extends BOBase {
	
	@Autowired
	private DAOCourse course;
	
	@Autowired
	private DAOCourseItem courseItem;
	
	@Field("courses")
	public List<Course> queryCourseList(@Var("params") QueryCourseParam params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		return course.queryCourseList(params);
	}

	@Field("course")
	public Course getCourse(@Var("courseId") Long courseId)throws Exception{
		Course params = new Course();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setCourseId(courseId);
		return course.getCourse(params);
	}
	
	public List<CourseItem> queryCourseItemList(@Var("courseId") long courseId)throws Exception{
		QueryCourseParam params = new QueryCourseParam();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setCourseId(courseId);
		return courseItem.queryCourseItemList(params);
	}

	@Field("courseItem")
	public CourseItem getCourseItem(@Var("itemId") long itemId)throws Exception{
		CourseItem params = new CourseItem();
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setCourseItemId(itemId);
		return courseItem.getCourseItem(params);
	}

}
