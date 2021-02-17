package com.saas.goods.dao;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import com.saas.goods.request.PostAddressParam;
import com.saas.goods.vo.PostAddress;

public interface DAOPostAddress {

	@Select({"select * from goods_post_address where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public List<PostAddress> queryPostAddressList(PostAddressParam param)throws Exception;

	@Select({"select * from goods_post_address where tenant_id=#{tenantId} and customer_id=#{customerId} and address_id=#{addressId}"})
	public PostAddress getPostAddress (PostAddress address)throws Exception;

	
	public void addPostAddress(PostAddress address)throws Exception;
	
	
	public void updatePostAddress(PostAddress address)throws Exception;

}
