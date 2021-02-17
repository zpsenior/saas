package com.saas.school;

import java.util.HashMap;
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
			String keys = "";
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
			System.out.println("#" + tc.getDesc());
			System.out.println("create table " + name + "{");
			for(Member member : tc.getMembers()) {
				if(member.isMethod()|| member.getJoin() != null) {
					continue;
				}
				String fieldName = toUnderscore(member.getName());
				Field fld = member.getField();
				if(fld != null && fld.isKey()) {
					if(!"".equals(keys)) {
						keys += ", ";
					}
					keys += fieldName;
				}
				System.out.print("   ");
				System.out.print(String.format("%-15s", fieldName));
				System.out.print("   ");
				System.out.print(String.format("%-20s", convert(member.getValueTypeName()) + ","));
				if(fld != null) {
					System.out.print(String.format("#%-12s", fld.desc()));
				}
				System.out.println();
			}
			System.out.print("   ");
			System.out.println("primary key(" + keys + ")");
			System.out.println("};");
		}
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
	
	private static String convert(String type) {
		String value = maps.get(type);
		return value != null ? value : type;
	}
	
	private static final Map<String, String> maps = new HashMap<>();
	
	static {
		maps.put("long", "bigint");
		maps.put("int", "int");
		maps.put("byte", "smallint");
		maps.put("Date", "datetime");
		maps.put("String", "varchar()");
		maps.put("String[]", "varchar()");
	}

}
