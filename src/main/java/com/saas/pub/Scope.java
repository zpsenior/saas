package com.saas.pub;


import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type
public class Scope {
	
	@Field(desc="单位")
	private String unit;
	
	@Field(desc="最大值")
	private double max;
	
	@Field(desc="最小值")
	private double min;
	
	public Scope() {}
	
	public Scope(String param) {
		String[] params  = param.split(",");
		unit = params[0];
		min = Double.parseDouble(params[1]);
		max = Double.parseDouble(params[2]);
	}
	
	public final String toString() {
		return unit + "," + min + "," + max;
	}
	
	public boolean match(double value) {
		return value >= min && value < max;
	}
}
