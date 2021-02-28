package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryCourseParam;
import com.saas.training.vo.Course;

@Mapper
public interface DAOCourse {

	@Select({"select * from training_course where tenant_id=#{tenantId}"})
	public List<Course> queryCourseList(QueryCourseParam param)throws Exception;

	@Select({"select * from training_course where tenant_id=#{tenantId} and service_id=#{serviceId}"})
	public Course getCourse(Course param)throws Exception;

	@Insert({
		"insert into training_course(",
		"     tenant_id,  customer_id,  staff_id,  goods_id,  order_id,  order_item_id,  spent_count,  total_count,  create_date",
		")values(",
		"   #{tenantId},#{customerId},#{staffId},#{goodsId},#{orderId},#{orderItemId},#{spentCount},#{totalCount},   now()   )"
		})
	public void addCourse(Course param)throws Exception;
	
	@Update({
		"<script>",
		"update training_course",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' staffId != null'>staff_id=#{staffId}</if>",
		"   <if test=' goodsId != null'>goods_id=#{goodsId}</if>",
		"   <if test=' orderId != null'>order_id=#{orderId}</if>",
		"   <if test=' orderItemId != null'>order_item_id=#{orderItemId}</if>",
		"   <if test=' spentCount != null'>spent_count=#{spentCount}</if>",
		"   <if test=' totalCount != null'>total_count=#{totalCount}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and service_id=#{serviceId}",
		"</script>"
		})
	public void updateCourse(Course param)throws Exception;

}
