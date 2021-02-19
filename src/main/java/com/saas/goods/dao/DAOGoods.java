package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.Goods;

public interface DAOGoods {

	@Select({"<script>",
		"select * from goods where tenant_id=#{tenantId}",
		"and goodsStatus = '0'",
		"<if test='showParent==true'>and parent_id is null</if>",
		"<if test='goodsTitle!=null'>and goods_title like '%${goodsTitle}%'</if>",
		"order by goodsId desc",
		"</script>"})
	public List<Goods> queryGoodsList(QueryGoodsParam param)throws Exception;

	@Select({"select * from goods where tenant_id=#{tenantId} and parentId=#{parentId}",
		"and goodsStatus = '0'",
		"order by goodsId desc"})
	public List<Goods> queryGoodsChildList(Goods params)throws Exception;

	@Select({"select * from goods where tenant_id=#{tenantId} and goods_id=#{goodsId}"})
	public Goods getGoods(Goods goods)throws Exception;

	public void addGoods(Goods goods)throws Exception;
	
	public void updateGoods(Goods goods)throws Exception;
	

}
