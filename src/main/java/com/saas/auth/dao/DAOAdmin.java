package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.request.QueryAdminParam;
import com.saas.auth.vo.Admin;

@Mapper
public interface DAOAdmin {

	@Select({"select * from auth_admin"})
	public List<Admin> queryAdminList(QueryAdminParam param)throws Exception;

	@Select({"select * from auth_admin where admin_id=#{id}"})
	public Admin getAdmin(long adminId)throws Exception;

	@Insert({
		"insert into auth_admin(",
		"     user_id,  login_name,   nickname,   mobile,   email,   status,  create_date,  admin_roles",
		")values(",
		"   #{userId},#{loginName},#{nickname},#{mobile},#{email},#{status},   now()   ,#{adminRoles})"
		})
	public void addAdmin(Admin admin)throws Exception;
	
	@Update({
		"<script>",
		"update auth_admin",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' userId != null'>user_id=#{userId}</if>",
		"   <if test=' loginName != null'>login_name=#{loginName}</if>",
		"   <if test=' nickname != null'>nickname=#{nickname}</if>",
		"   <if test=' mobile != null'>mobile=#{mobile}</if>",
		"   <if test=' email != null'>email=#{email}</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"   <if test='true'>update_date=now()</if>",
		"   <if test=' adminRoles != null'>admin_roles=#{adminRoles}</if>",
		"</trim>",
		"where admin_id=#{adminId}",
		"</script>"
		})
	public void updateAdmin(Admin admin)throws Exception;

}
