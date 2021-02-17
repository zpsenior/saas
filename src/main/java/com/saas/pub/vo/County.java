package com.saas.pub.vo;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;


@Data
@Type("County")
public class County {

	@Field
	private int countyId;

	@Field
	private String countyName;

}
