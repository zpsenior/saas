package com.saas.floweret.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.saas.floweret.request.QueryGroupParam;
import com.saas.floweret.vo.Group;

@Mapper
public interface DAOGroup {

	List<Group> queryGroupList(QueryGroupParam params)throws Exception;

	List<Group> queryMyGroupList(QueryGroupParam params)throws Exception;

	Group selectOne(Group params)throws Exception;

	void update(Group params);

}
