package com.saas.goods.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.goods.dao.DAOGoods;
import com.saas.goods.vo.Goods;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("MutationGoods")
public class MutationGoods {
	
	@Autowired
	private DAOGoods goods;
	
	@Field("create")
	public boolean createGoods(@Var("params") Goods params)throws Exception{
		goods.addGoods(params);
		return true;
	}
	
	@Field("update")
	public boolean updateGoods(@Var("params") Goods params)throws Exception{
		goods.updateGoods(params);
		return true;
	}

}
