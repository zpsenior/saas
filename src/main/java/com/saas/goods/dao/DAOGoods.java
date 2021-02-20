package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.QueryGoodsParam;
import com.saas.goods.vo.Goods;

@Mapper
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

	@Insert({
		"<script>",
		"insert into goods(",
		"     tenant_id,  goods_title,   description,   property,   img,   price,   discount,  parent_id,  category_id,   entity,  goods_status,  create_date",
		")values(",
		"   #{tenantId},#{goodsTitle},#{description},#{property},#{img},#{price},#{discount},#{parentId},#{categoryId},#{entity},#{goodsStatus},   now()   )",
		"</script>"
		})
	public void addGoods(Goods goods)throws Exception;
	
	@Update({
		"update goods",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' goodsTitle != null'>goods_title=#{goodsTitle}</if>",
		"   <if test=' description != null'>description=#{description}</if>",
		"   <if test=' property != null'>property=#{property}</if>",
		"   <if test=' img != null'>img=#{img}</if>",
		"   <if test=' price != null'>price=#{price}</if>",
		"   <if test=' discount != null'>discount=#{discount}</if>",
		"   <if test=' parentId != null'>parent_id=#{parentId}</if>",
		"   <if test=' categoryId != null'>category_id=#{categoryId}</if>",
		"   <if test=' entity != null'>entity=#{entity}</if>",
		"   <if test=' goodsStatus != null'>goods_status=#{goodsStatus}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and goods_id=#{goodsId}"
		})
	public void updateGoods(Goods goods)throws Exception;
	

}
