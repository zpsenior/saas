package com.saas.profitshare.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saas.profitshare.vo.UserRank;

@Mapper
public interface DAOUserRank {

	public List<UserRank> queryMyReferenceList(UserRank params)throws Exception;

	public UserRank getUserRank(UserRank params)throws Exception;
	
	public void addUserRank(UserRank params)throws Exception;
	
	public void updateUserRank(UserRank params)throws Exception;
}
