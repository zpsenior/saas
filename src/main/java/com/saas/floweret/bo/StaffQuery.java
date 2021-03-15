package com.saas.floweret.bo;

import java.util.List;

import org.springframework.stereotype.Service;

import com.saas.floweret.request.QueryCircleParam;
import com.saas.floweret.vo.MemberCircle;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

@Service
@Type
public class StaffQuery extends BOBase {


	@Field("circles")
	public List<MemberCircle> queryCircleList(QueryCircleParam params)throws Exception{
		return null;
	}
}
