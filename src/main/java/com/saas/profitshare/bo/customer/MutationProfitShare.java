package com.saas.profitshare.bo.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.CustomerSession;
import com.saas.profitshare.dao.DAOProfitShare;
import com.saas.profitshare.dao.DAOUserProfit;
import com.saas.profitshare.dao.DAOUserRank;
import com.saas.profitshare.vo.ProfitShare;
import com.saas.profitshare.vo.UserProfit;
import com.saas.profitshare.vo.UserRank;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component
public class MutationProfitShare extends BOBase {
	
	@Autowired
	private DAOProfitShare profitShare;
	
	@Autowired
	private DAOUserProfit userProfit;
	
	@Autowired
	private DAOUserRank userRank;

	public boolean addProfitShare()throws Exception{
		ProfitShare params = new ProfitShare();
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		//params.setOwnerId(session.getCustomerId());
		profitShare.addProfitShare(params);
		return true;
	}
	


	public boolean addUserProfit(UserProfit params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		userProfit.addUserProfit(params);
		return true;
	}
	
	public boolean addUserRank(UserRank params)throws Exception{
		CustomerSession session = getCustomerSession();
		params.setTenantId(session.getTenantId());
		userRank.addUserRank(params);
		return true;
	}
}
