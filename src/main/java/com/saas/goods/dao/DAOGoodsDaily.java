package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.QueryGoodsDailyParam;
import com.saas.goods.vo.GoodsDaily;

@Mapper
public interface DAOGoodsDaily {

	@Select("select * fromn goods_daily where tenant_id=#{tenantId} and goods_id=#{goodsId}")
	public List<GoodsDaily> queryGoodsDailyList(QueryGoodsDailyParam params)throws Exception;

	@Select("select * fromn goods_daily where tenant_id=#{tenantId} and goods_id=#{goodsId} and trade_date=#{tradeDate}")
	public GoodsDaily getGoodsDaily(GoodsDaily daily)throws Exception;
}
