package com.saas.floweret.bo;

import java.util.List;

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
import com.saas.floweret.request.QueryCircleParam;
import com.saas.floweret.request.QueryCircleReviewParam;
import com.saas.floweret.request.QueryGroupParam;
import com.saas.floweret.request.QueryMemberParam;
import com.saas.floweret.vo.Group;
import com.saas.floweret.vo.GroupMember;
import com.saas.floweret.vo.MemberCircle;
import com.saas.floweret.vo.MemberCircleReview;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

@Service
@Type
public class CustomerQuery extends BOBase {
	
	@Autowired
	private DAOGroup group;
	
	@Autowired
	private DAOGroupMember groupMember;
	
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

	@Field("groups")
	public List<Group> queryGroupList(QueryGroupParam params)throws Exception{
		return null;
	}

	@Field("group")
	public Group getGroup(long groupId)throws Exception{
		return null;
	}

	@Field("groupMembers")
	public List<GroupMember> queryGroupMmeberList(QueryMemberParam params)throws Exception{
		return null;
	}

	@Field("groupMember")
	public GroupMember getGroupMember(long groupId, long customerId)throws Exception{
		return null;
	}

	@Field("circles")
	public List<MemberCircle> queryCircleList(QueryCircleParam params)throws Exception{
		return null;
	}

	@Field("myCircles")
	public List<MemberCircle> queryMyCircleList(QueryCircleParam params)throws Exception{
		return null;
	}
	
	public List<MemberCircleReview> queryCircleReviewList(QueryCircleReviewParam params)throws Exception{
		return null;
	}
	
	public int getCirclePraiseCount(long circleId)throws Exception{
		return 0;
	}
}
