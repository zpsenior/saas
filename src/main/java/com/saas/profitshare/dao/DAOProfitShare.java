package com.saas.profitshare.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saas.profitshare.request.QueryProfitParam;
import com.saas.profitshare.vo.ProfitShare;

@Mapper
public interface DAOProfitShare {

	public List<ProfitShare> searchMyProfitShareList(QueryProfitParam params)throws Exception;
	
	public void addProfitShare(ProfitShare params)throws Exception;

}
