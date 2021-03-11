package com.saas.floweret.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.floweret.dao.DAOGroup;
import com.saas.floweret.dao.DAOGroupMember;
import com.saas.floweret.dao.DAOGroupMemberAttention;
import com.saas.floweret.dao.DAOMemberAttention;
import com.saas.floweret.dao.DAOMemberCircle;
import com.saas.floweret.dao.DAOMemberCircleReview;
import com.saas.floweret.dao.DAOMemberInfo;
import com.saas.floweret.dao.DAOMemberRequest;
import com.zpsenior.graphql4j.annotation.Type;

@Service
@Type
public class CustomerMutation {
	
	@Autowired
	private DAOGroup group;
	
	@Autowired
	private DAOGroupMember member;
	
	@Autowired
	private DAOGroupMemberAttention groupMemberAttention;
	
	@Autowired
	private DAOMemberInfo memberInfo;
	
	@Autowired
	private DAOMemberRequest memberRequest;
	
	@Autowired
	private DAOMemberAttention memberAttention;
	
	@Autowired
	private DAOMemberCircle memberCircle;
	
	@Autowired
	private DAOMemberCircleReview memberCircleReview;
}
