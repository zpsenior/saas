package com.saas.pub;

import java.math.BigDecimal;

public class Currency {

	
	private BigDecimal value;
	
	public Currency() {
		this.value = BigDecimal.ZERO;
	}

	public Currency(String amount) {
		if(amount == null){
			this.value = BigDecimal.ZERO;
		}else{
			this.value = new BigDecimal(amount);
		}
	}
	
	public Currency(Double amount) {
		if(amount == null){
			this.value = BigDecimal.ZERO;
		}else{
			this.value = new BigDecimal(amount);
		}
	}
	
	public BigDecimal getValue() {
		return this.value;
	}

	public double doubleValue() {
		return this.value.doubleValue();
	}
	
	public String toChineseString() {
		StringBuffer sb = new StringBuffer();
		return sb.toString();
	}
}
