package com.saas.training.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryCourseStudentParam")
public class QueryCourseStudentParam extends QueryParam {

	public QueryCourseStudentParam() {
		super("customet_id");
	}

	private String tenantId;

	private long courseId;
}
