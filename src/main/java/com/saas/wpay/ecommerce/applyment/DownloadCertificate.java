package com.saas.wpay.ecommerce.applyment;

import com.saas.wpay.WPayRequest;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class DownloadCertificate extends WPayRequest {

	public DownloadCertificate() {
		super("certificates", GET);
	}

}
