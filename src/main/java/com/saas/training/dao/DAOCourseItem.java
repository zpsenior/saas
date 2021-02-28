package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryCourseParam;
import com.saas.training.vo.CourseItem;

@Mapper
public interface DAOCourseItem {

	@Select({"select * from training_course_item where tenant_id=#{tenantId} and order_id=#{orderId}"})
	public List<CourseItem> queryCourseItemList(QueryCourseParam param)throws Exception;

	@Select({"select * from training_course_item where tenant_id=#{tenantId} and service_id=#{serviceId} and item_id=#{itemId}"})
	public CourseItem getCourseItem(CourseItem item)throws Exception;

	@Insert({
		"insert into training_course_item(",
		"     tenant_id,  service_id,  staff_id,  customer_id,  begin_time,  end_time,  sign_in,   imgs,  create_date",
		")values(",
		"   #{tenantId},#{serviceId},#{staffId},#{customerId},#{beginTime},#{endTime},#{signIn},#{imgs},   now()   )"
		})
	public void addCourseItem(CourseItem item)throws Exception;
	
	@Update({
		"<script>",
		"update training_course_item",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' staffId != null'>staff_id=#{staffId}</if>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' beginTime != null'>begin_time=#{beginTime}</if>",
		"   <if test=' endTime != null'>end_time=#{endTime}</if>",
		"   <if test=' signIn != null'>sign_in=#{signIn}</if>",
		"   <if test=' imgs != null'>imgs=#{imgs}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and service_id=#{serviceId}",
		"  and service_item_id=#{serviceItemId}",
		"</script>"
		})
	public void updateCourseItem(CourseItem item)throws Exception;

}
