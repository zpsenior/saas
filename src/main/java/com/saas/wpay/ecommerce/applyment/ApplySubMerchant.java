package com.saas.wpay.ecommerce.applyment;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ApplySubMerchant extends WPayRequest {
	
	@Data
	public class BusinessLicense{
		private String business_license_copy;
		private String business_license_number;
		private String merchant_name;
		private String legal_person;
		private String company_address;
		private String business_time;
	}
	
	@Data
	public class OrganizationCert{
		private String organization_copy;
		private String organization_number;
		private String organization_time;
	}
	
	@Data
	public class IdCard{
		private String id_card_copy;
		private String id_card_national;
		private String id_card_name;
		private String id_card_number;
		private String id_card_valid_time;
	}
	
	@Data
	public class IdDocInfo{
		private String id_doc_name;
		private String id_doc_number;
		private String id_doc_copy;
		private String doc_period_end;
		private boolean need_account_info;
	}
	
	@Data
	public class AccountInfo{
		private String bank_account_type;
		private String account_bank;
		private String account_name;
		private String bank_address_code;
		private String bank_branch_id;
		private String bank_name;
		private String account_number;
	}
	
	@Data
	public class ContactInfo{
		private String contact_type;
		private String contact_name;
		private String contact_id_card_number;
		private String mobile_phone;
		private String contact_email;
	}
	
	@Data
	public class SalesSceneInfo{
		private String store_name;
		private String store_url;
		private String store_qr_code;
		private String mini_program_sub_appid;
		private String merchant_shortname;
	}

	public ApplySubMerchant() {
		super("ecommerce/applyments/", POST);
	}
	
	private String out_request_no;
	
	private String organization_type;
	
	private BusinessLicense business_license_info;
	
	private OrganizationCert organization_cert_info;
	
	private String id_doc_type;
	
	private IdCard id_card_info;
	
	private IdDocInfo id_doc_info;
	
	private AccountInfo account_info;

	private ContactInfo contact_info;
	
	private SalesSceneInfo sales_scene_info;
}
