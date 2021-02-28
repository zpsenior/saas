package com.saas.payment.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.payment.dao.DAOPayment;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Type;

@Type
@Component
public class MutationPayment extends BOBase {
	

	@Autowired
	private DAOPayment payment;

}
