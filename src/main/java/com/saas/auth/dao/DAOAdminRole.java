package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.vo.AdminRole;

@Mapper
public interface DAOAdminRole {
	
	@Select("select * from auth_admin_role where role_id in (${id})")
	public List<AdminRole> queryAdminRoleList(String roles)throws Exception;
	
	@Insert({
		"insert into auth_admin_role(",
		"      descript,   privileges,  create_date",
		")values(",
		"   #{descript},#{privileges},   now()   )"
		})
	public void addAdminRole(AdminRole role)throws Exception;
	
	@Update({
		"<script>",
		"update auth_admin_role",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' descript != null'>descript=#{descript}</if>",
		"   <if test=' privileges != null'>privileges=#{privileges}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where role_id=#{roleId}",
		"</script>"
		})
	public void updateAdminRole(AdminRole role)throws Exception;

}
