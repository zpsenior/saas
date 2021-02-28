package com.saas.payment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.payment.dao.DAOPayment;
import com.saas.payment.request.QueryPaymentNotifyParam;
import com.saas.payment.vo.PaymentNotify;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component
public class QueryPayment extends BOBase {
	

	@Autowired
	private DAOPayment payment;
	
	@Field
	public List<PaymentNotify> queryPaymentNotifyList(@Var("params") QueryPaymentNotifyParam params)throws Exception{
		return payment.queryPaymentNotifyList(params);
	}

}
