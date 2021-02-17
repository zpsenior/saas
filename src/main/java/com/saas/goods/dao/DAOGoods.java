package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.Goods;

public interface DAOGoods {

	@Select({"select * from goods where tenant_id=#{tenantId}"})
	public List<Goods> queryGoodsList(QueryGoodsParam param)throws Exception;

	@Select({"select * from goods where tenant_id=#{tenantId} and goods_id=#{goodsId}"})
	public Goods getGoods(Goods goods)throws Exception;

	public void addGoods(Goods goods)throws Exception;
	
	
	public void updateGoods(Goods goods)throws Exception;

}
