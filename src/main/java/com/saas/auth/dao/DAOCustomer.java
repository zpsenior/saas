package com.saas.auth.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.saas.auth.request.QueryCustomerParam;
import com.saas.auth.vo.Customer;

@Mapper
public interface DAOCustomer {

	@Select({"select * from auth_customer where tenant_id=#{tenantId}"})
	public List<Customer> queryCustomerList(QueryCustomerParam param)throws Exception;

	@Select({"select * from auth_customer where tenant_id=#{tenantId} and customer_id=#{customerId}"})
	public Customer getCustomer(Customer customer)throws Exception;

	@Insert({
		"insert into auth_customer(",
		"     tenant_id,  user_id,   openid,  login_name,   nickname,   mobileno,   email,  create_date,   status",
		")values(",
		"   #{tenantId},#{userId},#{openid},#{loginName},#{nickname},#{mobileno},#{email},   now()   ,#{status})"
		})
	public void addCustomer(Customer customer)throws Exception;
	
	@Update({
		"<script>",
		"update auth_customer",
		"<trim prefix='set' suffixOverrides=','>",
		"   <if test=' userId != null'>user_id=#{userId}</if>",
		"   <if test=' openid != null'>openid=#{openid}</if>",
		"   <if test=' loginName != null'>login_name=#{loginName}</if>",
		"   <if test=' nickname != null'>nickname=#{nickname}</if>",
		"   <if test=' mobileno != null'>mobileno=#{mobileno}</if>",
		"   <if test=' email != null'>email=#{email}</if>",
		"   <if test='true'>update_date=now()</if>",
		"   <if test=' status != null'>status=#{status}</if>",
		"</trim>",
		"where tenant_id=#{tenantId}",
		"  and customer_id=#{customerId}",
		"</script>"
		})
	public void updateCustomer(Customer customer)throws Exception;

}
