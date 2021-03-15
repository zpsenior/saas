package com.saas.floweret.vo;

import java.util.Date;
import java.util.List;

import com.saas.auth.vo.Gender;
import com.saas.auth.vo.Marriage;
import com.saas.pub.Scope;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.annotation.Join;
import com.zpsenior.graphql4j.annotation.Type;

import lombok.Data;

@Data
@Type(desc="个人信息表")
public class MemberInfo {

	@Field(isKey=true, desc="租户ID")
	private String tenantId;

	@Field(isKey=true, desc="用户ID")
	private long customerId;

	@Field(desc="微信id")
	private String openid;

	@Field(desc="昵称")
	private String nickname;

	@Field(desc="头像（照片文件保存路径）")
	private String headImg;

	@Field(desc="展现照片（照片文件保存路径）")
	private String[] imgs;

	@Field(desc="性别")
	private Gender gender;

	@Field(desc="生日")
	private String birthday;
	
	@Field(desc="是否显示年龄")
	private boolean showBirthday;

	@Field(desc="籍贯")
	private String nativePlace;
	
	@Field(desc="户籍")
	private String registeredResidence;

	@Field(desc="学历")
	private String education;

	@Field(desc="职业")
	private String occupation;

	@Field(desc="身高(cm)")
	private int height;

	@Field(desc="体重(斤)")
	private int weight;

	@Field(desc="月薪范围")
	private Scope salaryScope;

	@Field(desc="联系方式")
	private String contact;

	@Field(desc="婚姻状况")
	private Marriage marriage;

	@Field(desc="是否有车")
	private boolean owningCar;

	@Field(desc="车牌注册地")
	private String carLocation;

	@Field(desc="是否有房")
	private boolean owningHouse;

	@Field(desc="房屋地点")
	private String houseLocation;
	
	@Field(desc="自我介绍")
	private String description;
	
	@Field(desc="个性宣言（声音文件保存路径）")
	private String voice;
	
	@Field(desc="被关注次数")
	private int attentionMeCount;
	
	@Field(desc="关注次数")
	private int attentionOtherCount;

	@Field(desc="修改日期")
	private Date updateDate;

	@Field(desc="创建日期")
	private Date createDate;
	
	@Join(request = "queryMyHobbyList", params= {"tenantId", "customerId"})
	private List<MemberHobby> hobies;
	
	@Join(request = "getMyRequest", params= {"tenantId", "customerId"})
	private MemberRequest request;

}
