package com.saas.pub.vo;

import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type("Province")
public class Province {

	@Field
	private int provinceId;

	@Field
	private String provinceName;
	
	@Field
	private List<City> cties;
}
