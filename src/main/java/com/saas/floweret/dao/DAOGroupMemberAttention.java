package com.saas.floweret.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.saas.floweret.vo.GroupMemberAttention;

@Mapper
public interface DAOGroupMemberAttention {

	@Select({"select a.customerId, b.customerId ",
		"from group_member_attention a inner join group_member_attention b",
		"on a.customer_id=b.attention_customer_id and b.customer_id=a.attention_customer_id",
		"where a.tenant_id=#{tenantId} and b.tenant_id=#{tenantId} ",
		"and a.group_id=#{groupId} and b.group_id=#{groupId} ",
		"and a.customer_id!=b.customer_id"})
	List<GroupMemberAttention> queryMatchMemberList()throws Exception;
}
