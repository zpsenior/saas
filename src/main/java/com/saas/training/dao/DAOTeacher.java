package com.saas.training.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.training.request.QueryTeacherParam;
import com.saas.training.vo.Teacher;

@Mapper
public interface DAOTeacher {
	
	@Select("select * from training_teacher where tenant_id=#{tenantId}")
	public List<Teacher> queryTeacherList(QueryTeacherParam params)throws Exception;

	@Select("select * from training_teacher where tenant_id=#{tenantId} and staff_id=#{staffId}")
	public Teacher getTeacher(Teacher teacher)throws Exception;
	
	@Insert({
		"insert into training_teacher(",
		"     tenant_id,  staff_id,   title,   character,   descript,   portrait,   graduation,   certificate,   specialty,   prize,   status,  create_date",
		")values(",
		"   #{tenantId},#{staffId},#{title},#{character},#{descript},#{portrait},#{graduation},#{certificate},#{specialty},#{prize},#{status},   now()   )"
		})
	public void addTeacher(Teacher teacher)throws Exception;
	
	@Update({
		"<script>",
		"update training_teacher",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' title != null'>title=#{title}</if>",
		"   <if test=' character != null'>character=#{character}</if>",
		"   <if test=' descript != null'>descript=#{descript}</if>",
		"   <if test=' portrait != null'>portrait=#{portrait}</if>",
		"   <if test=' graduation != null'>graduation=#{graduation}</if>",
		"   <if test=' certificate != null'>certificate=#{certificate}</if>",
		"   <if test=' specialty != null'>specialty=#{specialty}</if>",
		"   <if test=' prize != null'>prize=#{prize}</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and staff_id=#{staffId}",
		"</script>"
		})
	public void updateTeacher(Teacher teacher)throws Exception;

}
