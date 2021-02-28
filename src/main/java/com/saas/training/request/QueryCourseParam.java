package com.saas.training.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryCourseParam")
public class QueryCourseParam extends QueryParam {

	public QueryCourseParam() {
		super("course_id");
	}
	
	private String tenantId;
	
	private long customerId;
	
	private long courseId;

}
