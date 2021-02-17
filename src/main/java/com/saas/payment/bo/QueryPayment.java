package com.saas.payment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.payment.dao.DAOPayment;
import com.saas.payment.request.QueryCallbackParam;
import com.saas.payment.vo.PaymentCallback;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component
public class QueryPayment {
	

	@Autowired
	private DAOPayment payment;
	
	@Field
	public List<PaymentCallback> queryCallBackList(@Var("params") QueryCallbackParam params)throws Exception{
		return payment.queryCallBackList(params);
	}

}
