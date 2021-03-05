package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saas.training.request.QueryCourseStudentParam;
import com.saas.training.vo.CourseStudent;

@Mapper
public interface DAOCourseStudent {

	public List<CourseStudent> queryCourseStudentList(QueryCourseStudentParam param)throws Exception;
	
	public CourseStudent getCourseStudent(CourseStudent param)throws Exception;
	
	public void addCourseStudent(CourseStudent param)throws Exception;
	
	public void updateCourseStudent(CourseStudent param)throws Exception;
}
