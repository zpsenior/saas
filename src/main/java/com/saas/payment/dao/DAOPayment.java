package com.saas.payment.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.payment.request.QueryPaymentNotifyParam;
import com.saas.payment.vo.PaymentNotify;

public interface DAOPayment {
	
	@Select({"select * from payment_notify where tenant_id=#{tenantId}"})
	public List<PaymentNotify> queryPaymentNotifyList(QueryPaymentNotifyParam params);
	
	@Select({"select * from payment_notify where tenant_id=#{tenantId} and out_trade_no=#{outTradeNo}"})
	public PaymentNotify getPaymentNotify(PaymentNotify params);
	
	@Insert({
		"insert into payment_notify(",
		"     tenant_id,  transaction_id,  out_trade_no,  create_date",
		")values(",
		"   #{tenantId},#{transactionId},#{outTradeNo},   now()   )"
		})
	public void addPaymentNotify(PaymentNotify params);
	
	@Update({
		"update payment_notify",
		"<trim prefix='set' suffixOverrides=','>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and out_trade_no=#{outTradeNo}"
		})
	public void updatePaymentNotify(PaymentNotify params);

}
