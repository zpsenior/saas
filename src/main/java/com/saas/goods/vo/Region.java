package com.saas.goods.vo;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;


@Data
@Type(desc="区域地址表")
public class Region {

	@Field(desc="省")
	private String province;

	@Field(desc="地市")
	private String city;

	@Field(desc="区县")
	private String county;

}
