package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.saas.auth.request.QueryUserParam;
import com.saas.auth.vo.UserInfo;

@Mapper
public interface DAOUser {

	@Select({"select * from auth_user where tenant_id=#{tenantId}"})
	public List<UserInfo> queryUserInfoList(QueryUserParam param)throws Exception;

	@Select({"select * from auth_user where tenant_id=#{tenantId} and user_id=#{userId}"})
	public UserInfo getUserInfo(UserInfo user)throws Exception;

	public void addUserInfo(UserInfo user)throws Exception;
	
	public void updateUserInfo(UserInfo user)throws Exception;
}
