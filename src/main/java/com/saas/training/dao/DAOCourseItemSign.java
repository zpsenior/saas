package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saas.training.request.QueryCourseItemSignParam;
import com.saas.training.vo.CourseItemSign;

@Mapper
public interface DAOCourseItemSign {

	public List<CourseItemSign> queryCourseItemSignList(QueryCourseItemSignParam param)throws Exception;
	
	public CourseItemSign getCourseItemSign(CourseItemSign param)throws Exception;
	
	public void addCourseItemSign(CourseItemSign param)throws Exception;
	
	public void updateCourseItemSign(CourseItemSign param)throws Exception;
}
