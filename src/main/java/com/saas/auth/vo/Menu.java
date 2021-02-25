package com.saas.auth.vo;

import java.util.Date;

import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="菜单表", incr="MenuId")
public class Menu {

	@Field(isKey=true, desc="菜单ID")
	private long menuId;

	@Field(desc="菜单描述", len=100)
	private String  title;

	@Field(desc="菜单对应路径", len=100)
	private String url;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;

}
