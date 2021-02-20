package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryTeacherReviewParam;
import com.saas.training.vo.TeacherReview;

public interface DAOTeacherReview {
	
	@Select("select * from training_teacher_review where tenant_id=#{tenantId} and staff_id=#{staffId}")
	public List<TeacherReview> queryTeacherReviewList(QueryTeacherReviewParam params)throws Exception;
	
	@Select("select * from training_teacher_review where tenant_id=#{tenantId} and staff_id=#{staffId} and review_id=#{reviewId}")
	public void getTeacherReview(TeacherReview params)throws Exception;
	
	@Insert({
		"insert into training_teacher_review(",
		"     tenant_id,  staff_id,  customer_id,   content,   status,  create_date",
		")values(",
		"   #{tenantId},#{staffId},#{customerId},#{content},#{status},   now()   )"
		})
	public void addTeacherReview(TeacherReview params)throws Exception;
	
	@Update({
		"update training_teacher_review",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' customerId != null'>customer_id=#{customerId}</if>",
		"   <if test=' content != null'>content=#{content}</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and staff_id=#{staffId}",
		"  and review_id=#{reviewId}"
		})
	public void updateTeacherReview(TeacherReview params)throws Exception;

}
