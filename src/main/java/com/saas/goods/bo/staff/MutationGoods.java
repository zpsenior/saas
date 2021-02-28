package com.saas.goods.bo.staff;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.saas.auth.session.TenantStaffSession;
import com.saas.goods.dao.DAOGoods;
import com.saas.goods.dao.DAOGoodsCategory;
import com.saas.goods.vo.Goods;
import com.saas.goods.vo.GoodsCategory;
import com.saas.goods.vo.GoodsStatus;
import com.saas.pub.BOBase;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;
import com.zpsenior.graphql4j.annotation.Var;

@Type
@Component("Staff.MutationGoods")
public class MutationGoods extends BOBase {
	
	@Autowired
	private DAOGoods goods;
	
	@Autowired
	private DAOGoodsCategory category;
	
	@Field("create")
	public boolean createGoods(@Var("params") Goods params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		params.setStatus(GoodsStatus.APPLY);
		goods.addGoods(params);
		return true;
	}
	
	@Field("update")
	public boolean updateGoods(@Var("params") Goods params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		goods.updateGoods(params);
		return true;
	}

	@Field("createCategory")
	public boolean createCategory(@Var("params") GoodsCategory params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		category.addGoodsCategory(params);
		return true;
	}

	@Field("updateCategory")
	public boolean updateCategory(@Var("params") GoodsCategory params)throws Exception{
		TenantStaffSession session = getStaffSession();
		params.setTenantId(session.getTenantId());
		category.updateGoodsCategory(params);
		return true;
	}
}
