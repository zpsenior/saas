package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.goods.request.PostAddressParam;
import com.saas.goods.vo.PostAddress;

public interface DAOPostAddress {

	@Select({"select * from goods_post_address where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<PostAddress> queryPostAddressList(PostAddressParam param)throws Exception;

	@Select({"select * from goods_post_address where tenant_id=#{tenantId} and customer_id=#{customerId} and address_id=#{addressId}"})
	public PostAddress getPostAddress (PostAddress address)throws Exception;

	@Insert({
		"insert into goods_post_address(",
		"     tenant_id,  customer_id,   recipient,   gender,   mobile,   province,   city,   county,   detail,  create_date",
		")values(",
		"   #{tenantId},#{customerId},#{recipient},#{gender},#{mobile},#{province},#{city},#{county},#{detail},   now()   )"
		})
	public void addPostAddress(PostAddress address)throws Exception;
	
	@Update({
		"update goods_post_address",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' recipient != null'>recipient=#{recipient}</if>",
		"   <if test=' gender != null'>gender=#{gender}</if>",
		"   <if test=' mobile != null'>mobile=#{mobile}</if>",
		"   <if test=' province != null'>province=#{province}</if>",
		"   <if test=' city != null'>city=#{city}</if>",
		"   <if test=' county != null'>county=#{county}</if>",
		"   <if test=' detail != null'>detail=#{detail}</if>",
		"   <if test='true'>update_date=now()</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and customer_id=#{customerId}",
		"  and address_id=#{addressId}"
		})
	public void updatePostAddress(PostAddress address)throws Exception;
	
	@Delete({"delete from goods_post_address where tenant_id=#{tenantId} and customer_id=#{customerId} and address_id=#{addressId}"})
	public void removePostAddress(PostAddress address)throws Exception;

}
