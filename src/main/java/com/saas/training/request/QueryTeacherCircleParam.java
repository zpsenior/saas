package com.saas.training.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryTeacherCircleParam")
public class QueryTeacherCircleParam extends QueryParam {
	
	private String tenant;
	
	private String staffId;
	
	public QueryTeacherCircleParam() {
		super("circle_id");
	}

}
