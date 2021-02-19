package com.saas.payment.dao;

import java.util.List;

import com.saas.payment.request.QueryPaymentNotifyParam;
import com.saas.payment.vo.PaymentNotify;

public interface DAOPayment {
	
	public List<PaymentNotify> queryPaymentNotifyList(QueryPaymentNotifyParam params);

}
