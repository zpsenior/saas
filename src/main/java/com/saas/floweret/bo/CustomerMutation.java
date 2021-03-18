package com.saas.floweret.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saas.auth.session.CustomerSession;
import com.saas.floweret.dao.DAOGroup;
import com.saas.floweret.dao.DAOGroupMember;
import com.saas.floweret.dao.DAOGroupMemberAttention;
import com.saas.floweret.dao.DAOMemberAttention;
import com.saas.floweret.dao.DAOMemberCircle;
import com.saas.floweret.dao.DAOMemberCircleReview;
import com.saas.floweret.dao.DAOMemberInfo;
import com.saas.floweret.dao.DAOMemberRequest;
import com.saas.floweret.vo.Group;
import com.saas.floweret.vo.GroupMember;
import com.saas.floweret.vo.MemberStatus;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Service
@Type
public class CustomerMutation extends BOBase {
	
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
	
	public void enterGroup(@Var("groupId") long groupId, @Var("introduce") long introducer, @Var("payAmount") long payAmount)throws Exception {
		CustomerSession session = getCustomerSession();
		Group params = new Group();
		params.setGroupId(groupId);
		params.setTenantId(session.getTenantId());
		params = group.selectOne(params);
		if(params == null) {
			throw new RuntimeException("can not find group by id:" + groupId);
		}
		if(session.isMale()) {
			if(params.getMaleCount() >= params.getMaxMaleCount()) {
				throw new RuntimeException("more than max:" + params.getMaxMaleCount());
			}
			params.setMaleCount(params.getMaleCount() + 1);
		}else{
			if(params.getFemaleCount() >= params.getMaxFemaleCount()) {
				throw new RuntimeException("more than max:" + params.getMaxFemaleCount());
			}
			params.setFemaleCount(params.getFemaleCount() + 1);
		}
		group.update(params);
		GroupMember member = new GroupMember();
		member.setGroupId(groupId);
		member.setTenantId(session.getTenantId());
		member.setMemberId(session.getCustomerId());
		member.setIntroducer(introducer);
		member.setStatus(MemberStatus.unpayed);
		member.setPayAmount(payAmount);
		String outTradeNo = "G" + groupId + "M" + member.getMemberId() + "X" + System.currentTimeMillis();
		member.setOutTradeNo(outTradeNo);
		groupMember.add(member);
	}
}
