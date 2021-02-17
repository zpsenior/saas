package com.saas.pub.vo;

import java.util.List;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type("City")
public class City {

	@Field
	private int cityId;

	@Field
	private String cityName;
	
	@Field
	private List<County> counties;

}
