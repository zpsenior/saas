package com.saas.school;

import java.io.PrintWriter;
import java.io.StringWriter;
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
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			build(pw, AdminQuery.class, AdminMutation.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.flush();
		System.out.println(sw.toString());
	}
	
	private static void build(PrintWriter pw, Class<?> query, Class<?> mutation)throws Exception {
		Schema schema = new Schema(query, mutation);
		System.out.println(schema.toString());
		for(String name : schema.getTypeNames()) {
			if(name.startsWith("Query") || name.startsWith("Mutation")) {
				continue;
			}
			TypeConfig tc = schema.getTypeConfig(name);
			String className = tc.getBindClass().getName();
			name = getTableName(name, className);
			String desc = tc.getAnn().desc();
			String incr = toUnderscore(tc.getAnn().incr());
			pw.println("#" + desc);
			pw.println("drop table `" + name + "`;");
			pw.println("create table `" + name + "`(");
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
				pw.print("   ");
				pw.print(String.format("%-17s", "`" + fieldName + "`"));
				pw.print("   ");
				String autoIncr = "";
				if(fieldName.equals(incr)) {
					autoIncr = " auto_increment";
				}
				pw.print(String.format("%-21s", convert(member.getValueTypeName(), fld.len()) + autoIncr));
				if(!"".equals(fld.desc())) {
					pw.print(" comment '");
					pw.print(fld.desc());
					pw.print("'");
				}
				pw.println(",");
			}
			pw.print("   ");
			if(!"".equals(incr)) {
				pw.print("primary key(`" + incr + "`)");
				if(keys.size() > 1) {
					pw.println(",");
					pw.print("   ");
					pw.println("unique  key(" + toStr(keys) + ")");
				}else {
					pw.println();
				}
			}else{
				pw.println("primary key(" + toStr(keys) + ")");
			}
			pw.print(") comment'");
			pw.print(desc);
			pw.println("';");
			if(!"".equals(incr)) {
				pw.println("alter table `" + name  + "` AUTO_INCREMENT=10000;");
			}
		}
	}

	private static String getTableName(String name, String className) {
		name = toUnderscore(name);
		if(className.startsWith("com.saas.auth")) {
			name = "auth_" + name;
		}else if(className.startsWith("com.saas.goods")) {
			if(!name.startsWith("goods")) {
				name = "goods_" + name;
			}
		}else if(className.startsWith("com.saas.training")) {
			name = "training_" + name;
		}
		return name;
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
