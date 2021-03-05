package com.saas.training.bo.customer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.pub.BOBase;
import com.saas.training.dao.DAOCourse;
import com.saas.training.dao.DAOCourseItem;
import com.saas.training.request.QueryCourseParam;
import com.saas.training.vo.Course;
import com.saas.training.vo.CourseItem;
import com.saas.training.vo.CourseStudentSchedule;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.QueryCourse")
public class QueryCourse extends BOBase {
	
	@Autowired
	private DAOCourse course;
	
	@Autowired
	private DAOCourseItem courseItem;

	
	@Field("courses")
	public List<Course> queryCourseList(@Var("params") QueryCourseParam params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		return course.queryCourseList(params);
	}

	@Field("course")
	public Course getCourse(@Var("courseId") long courseId)throws Exception{
		Course params = new Course();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCourseId(courseId);
		return course.getCourse(params);
	}
	
	public List<CourseItem> queryCourseItemList(@Var("courseId") long courseId)throws Exception{
		QueryCourseParam params = new QueryCourseParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCourseId(courseId);
		return courseItem.queryCourseItemList(params);
	}

	@Field("courseItem")
	public CourseItem getCourseItem(@Var("itemId") long itemId)throws Exception{
		CourseItem params = new CourseItem();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCourseItemId(itemId);
		return courseItem.getCourseItem(params);
	}

	@Field("courseSchedules")
	public List<CourseStudentSchedule> queryCourseScheduleList()throws Exception{
		QueryCourseParam params = new QueryCourseParam();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		List<CourseItem> items = courseItem.queryCourseItemList(params);
		Map<Date, CourseStudentSchedule> maps = new TreeMap<>();
		for(CourseItem item : items) {
			Date dt = item.getAppointDate();
			CourseStudentSchedule schedule = maps.get(dt);
			if(schedule == null) {
				schedule = new CourseStudentSchedule();
				schedule.setAppointDate(dt);
				maps.put(dt, schedule);
			}
			schedule.addItem(item);
		}
		return new ArrayList<CourseStudentSchedule>(maps.values());
	}
}
