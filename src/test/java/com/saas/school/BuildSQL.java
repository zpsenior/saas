package com.saas.school;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.saas.training.AdminMutation;
import com.saas.training.AdminQuery;
import com.zpsenior.graphql4j.annotation.Field;
import com.zpsenior.graphql4j.schema.Member;
import com.zpsenior.graphql4j.schema.Schema;
import com.zpsenior.graphql4j.schema.TypeConfig;

public class BuildSQL {

	public static void main(String[] args) {
		try {
			build(AdminQuery.class, AdminMutation.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void build(Class<?> query, Class<?> mutation)throws Exception {
		Schema schema = new Schema(query, mutation);
		System.out.println(schema.toString());
		for(String name : schema.getTypeNames()) {
			if(name.startsWith("Query") || name.startsWith("Mutation")) {
				continue;
			}
			TypeConfig tc = schema.getTypeConfig(name);
			name = toUnderscore(name);
			String className = tc.getBindClass().getName();
			if(className.startsWith("com.saas.auth")) {
				name = "auth_" + name;
			}else if(className.startsWith("com.saas.goods")) {
				if(!name.startsWith("goods")) {
					name = "goods_" + name;
				}
			}else if(className.startsWith("com.saas.training")) {
				name = "training_" + name;
			}
			String desc = tc.getAnn().desc();
			String incr = toUnderscore(tc.getAnn().incr());
			System.out.println("#" + desc);
			System.out.println("drop table `" + name + "`;");
			System.out.println("create table `" + name + "`(");
			List<String> keys = new ArrayList<>();
			for(Member member : tc.getMembers()) {
				if(member.isMethod()|| member.getJoin() != null) {
					continue;
				}
				String fieldName = toUnderscore(member.getName());
				Field fld = member.getField();
				if(fld != null && fld.isKey()) {
					keys.add(fieldName);
				}
				System.out.print("   ");
				System.out.print(String.format("%-17s", "`" + fieldName + "`"));
				System.out.print("   ");
				String autoIncr = "";
				if(fieldName.equals(incr)) {
					autoIncr = " auto_increment";
				}
				System.out.print(String.format("%-21s", convert(member.getValueTypeName(), fld.len()) + autoIncr));
				if(!"".equals(fld.desc())) {
					System.out.print(" comment '");
					System.out.print(fld.desc());
					System.out.print("'");
				}
				System.out.println(",");
			}
			System.out.print("   ");
			if(!"".equals(incr)) {
				System.out.print("primary key(`" + incr + "`)");
				if(keys.size() > 1) {
					System.out.println(",");
					System.out.print("   ");
					System.out.println("unique  key(" + toStr(keys) + ")");
				}else {
					System.out.println();
				}
			}else{
				System.out.println("primary key(" + toStr(keys) + ")");
			}
			System.out.print(") comment'");
			System.out.print(desc);
			System.out.println("';");
			if(!"".equals(incr)) {
				System.out.println("alter table `" + name  + "` AUTO_INCREMENT=10000;");
			}
		}
	}

	private static String toStr(List<String> keys) {
		StringBuffer sb = new StringBuffer();
		for(String key : keys) {
			if(sb.length() > 0) {
				sb.append(", ");
			}
			sb.append("`");
			sb.append(key);
			sb.append("`");
		}
		return sb.toString();
	}

	private static String toUnderscore(String name) {
		StringBuffer sb = new StringBuffer();
		for(int i = 0; i < name.length(); i++) {
			char c = name.charAt(i);
			if(c >= 'A' && c <= 'Z') {
				if(i != 0) {
					sb.append("_");
				}
				c = Character.toLowerCase(c); 
			}
			sb.append(c);
		}
		return sb.toString();
	}
	
	private static String convert(String type, int len) {
		String value = maps.get(type);
		String val = value != null ? value : "char(1)";
		if(val.equals("varchar()")) {
			val = "varchar(" + len + ")";
		}
		return val;
	}
	
	private static final Map<String, String> maps = new HashMap<>();
	
	static {
		maps.put("long", "bigint");
		maps.put("int", "int");
		maps.put("byte", "smallint");
		maps.put("Date", "datetime");
		maps.put("String", "varchar()");
		maps.put("String[]", "varchar(1000)");
	}

}
