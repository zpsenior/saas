package com.saas.wpay.ecommerce.applyment;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ModifySettlement extends WPayRequest {
	
	public enum AccountType{
		ACCOUNT_TYPE_BUSINESS, //对公银行账户
		ACCOUNT_TYPE_PRIVATE, //经营者个人银行卡
	}

	public ModifySettlement() {
		super("apply4sub/sub_merchants/{sub_mchid}/modify-settlement", POST);
	}

	private String sub_mchid;

	private AccountType account_type;
	
	private String account_bank;
	
	private String bank_address_code;
	
	private String bank_name;
	
	private String bank_branch_id;
	
	private String account_number;
}
