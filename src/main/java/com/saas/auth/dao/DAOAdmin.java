package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.auth.request.QueryAdminParam;
import com.saas.auth.vo.Admin;

public interface DAOAdmin {

	@Select({"select * from auth_admin"})
	public List<Admin> queryAdminList(QueryAdminParam param)throws Exception;

	@Select({"select * from auth_admin where admin_id=#{id}"})
	public Admin getAdmin(long adminId)throws Exception;

	public void addAdmin(Admin admin)throws Exception;
	
	
	public void updateAdmin(Admin admin)throws Exception;

}
