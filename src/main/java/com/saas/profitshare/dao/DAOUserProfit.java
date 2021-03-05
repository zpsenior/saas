package com.saas.profitshare.dao;

import org.apache.ibatis.annotations.Mapper;

import com.saas.profitshare.vo.UserProfit;

@Mapper
public interface DAOUserProfit {

	public UserProfit getMyProfit(UserProfit params)throws Exception;

	public void addUserProfit(UserProfit params)throws Exception;

	public void updateUserProfit(UserProfit params)throws Exception;
}
