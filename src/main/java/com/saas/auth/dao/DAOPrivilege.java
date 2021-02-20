package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.request.QueryPrivilegeParam;
import com.saas.auth.vo.Privilege;
import com.saas.auth.vo.UserInfo;

public interface DAOPrivilege {

	@Select({"select * from auth_privilege where tenant_id=#{tenantId} and privilege_id in",
		" (select privilege_id from auth_user_privilege where user_id=#{userId})"})
	public List<Privilege> queryUserPrivilegeList(UserInfo user)throws Exception;

	@Select({"select * from auth_privilege where 1=1"})
	public List<Privilege> queryPrivilegeList(QueryPrivilegeParam params)throws Exception;

	@Select({"select * from auth_privilege where privilege_id=#{privilegeId}"})
	public Privilege getPrivilege(long privilegeId)throws Exception;
	
	@Insert({
		"insert into auth_privilege(",
		"      scope,   descript,   urls,  create_date",
		")values(",
		"   #{scope},#{descript},#{urls},   now()   )"
		})
	public void addPrivilege(Privilege params)throws Exception;
	
	@Update({
		"update auth_privilege",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' descript != null'>descript=#{descript}</if>",
		"   <if test=' urls != null'>urls=#{urls}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where privilege_id=#{privilegeId}",
		"  and scope=#{scope}"
		})
	public void updatePrivilege(Privilege params)throws Exception;

}
