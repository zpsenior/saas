package com.saas.training.request;

import com.saas.pub.QueryParam;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("QueryTeacherReviewParam")
public class QueryTeacherReviewParam extends QueryParam {
	
	private String tenantId;
	
	private long staffId;
	
	public QueryTeacherReviewParam() {
		super("review_id");
	}

}
