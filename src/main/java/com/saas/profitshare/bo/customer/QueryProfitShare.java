package com.saas.profitshare.bo.customer;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.profitshare.dao.DAOProfitShare;
import com.saas.profitshare.dao.DAOUserProfit;
import com.saas.profitshare.dao.DAOUserRank;
import com.saas.profitshare.request.QueryProfitParam;
import com.saas.profitshare.vo.ProfitShare;
import com.saas.profitshare.vo.UserProfit;
import com.saas.profitshare.vo.UserRank;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Customer.QueryProfitShare")
public class QueryProfitShare extends BOBase {
	
	@Autowired
	private DAOProfitShare profitShare;
	
	@Autowired
	private DAOUserProfit userProfit;
	
	@Autowired
	private DAOUserRank userRank;
	
	@Field("myProfitShares")
	public List<ProfitShare> searchMyProfitShareList(@Var("params") QueryProfitParam params) throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setOwnerId(session.getCustomerId());
		return profitShare.searchMyProfitShareList(params);
	}

	@Field("myProfit")
	public UserProfit getMyProfit() throws Exception{
		UserProfit params = new UserProfit();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return userProfit.getMyProfit(params);
	}
	
	@Field("myUserRank")
	public UserRank getMyUserRank()throws Exception{
		UserRank params = new UserRank();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return userRank.getUserRank(params);
	}

	public List<UserRank> queryMyReferenceList()throws Exception{
		UserRank params = new UserRank();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		params.setCustomerId(session.getCustomerId());
		return userRank.queryMyReferenceList(params);
	}
}
