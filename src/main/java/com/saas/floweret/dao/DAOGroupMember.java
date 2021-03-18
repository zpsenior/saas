package com.saas.floweret.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saas.floweret.request.QueryMemberParam;
import com.saas.floweret.vo.GroupMember;

@Mapper
public interface DAOGroupMember {

	List<GroupMember> queryGroupMemberList(QueryMemberParam params)throws Exception;

	GroupMember getGroupMember(GroupMember params)throws Exception;

	void add(GroupMember member)throws Exception;

}
