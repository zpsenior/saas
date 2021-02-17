package com.saas.payment.dao;

import java.util.List;

import com.saas.payment.request.QueryCallbackParam;
import com.saas.payment.vo.PaymentCallback;

public interface DAOPayment {
	
	public List<PaymentCallback> queryCallBackList(QueryCallbackParam params);

}
