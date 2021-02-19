package com.saas.goods.request;

import com.saas.goods.vo.Goods;
import com.zpsenior.graphql4j.annotation.Input;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Input("GoodsParam")
public class GoodsParam extends Goods{
	
	private long customerId;

}
