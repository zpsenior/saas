package com.saas.school;

import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import com.saas.training.AdminMutation;
import com.saas.training.AdminQuery;
import com.zpsenior.graphql4j.schema.Member;
import com.zpsenior.graphql4j.schema.Schema;
import com.zpsenior.graphql4j.schema.TypeConfig;

public class BuildCRUD {

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
			String incr = tc.getAnn().incr();
			name = getTableName(name, className);
			List<String> fields = new ArrayList<>();
			Set<String> keys = new LinkedHashSet<>();
			for(Member member : tc.getMembers()) {
				if(member.isMethod()|| member.getJoin() != null) {
					continue;
				}
				String fieldName = member.getName();
				if(member.getField().isKey()) {
					keys.add(fieldName);
				}
				if(fieldName.equals(incr)) {
					continue;
				}
				fields.add(fieldName);
			}
			pw.print("########");
			pw.print(name);
			pw.println("########");
			pw.println(wrapper("Insert", buildInsert(name, fields)));
			pw.println(wrapper("Update", buildUpdate(name, fields, keys)));
			pw.println(wrapper("Select", buildSelect(name, keys)));
			pw.println(wrapper("Delete", buildDelete(name, keys)));
			pw.println();
		}
	}

	private static String wrapper(String prefix, String str) throws Exception{
		LineNumberReader reader = new LineNumberReader(new StringReader(str));
		StringBuffer sb = new StringBuffer();
		sb.append("@").append(prefix).append("({\n");
		if("Update".equals(prefix)) {
			sb.append("\"<script>\",").append("\n");
		}
		while(true) {
			String line = reader.readLine();
			if(line == null) {
				break;
			}
			if("".equals(line)) {
				continue;
			}
			sb.append("\"").append(line).append("\",").append("\n");
		}
		if("Update".equals(prefix)) {
			sb.append("\"</script>\"");
		}else {
			sb.setLength(sb.length() - 2);
			
		}
		sb.append("\n").append("})");
		return sb.toString();
	}

	private static String buildInsert(String name, List<String> fields) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		pw.println("insert into " + name + "(");
		boolean first = true;
		for(String field : fields) {
			if("updateDate".equals(field)) {
				continue;
			}
			if(!first) {
				pw.print(",");
			}else {
				pw.print("   ");
			}
			String columnName = toUnderscore(field);
			if(columnName.indexOf('_') > 0) {
				pw.print("  ");
			}else {
				pw.print("   ");
			}
			pw.print(columnName);
			first = false;
		}
		pw.println();
		pw.println(")values(");
		first = true;
		for(String field : fields) {
			if("updateDate".equals(field)) {
				continue;
			}
			if(!first) {
				pw.print(",");
			}else {
				pw.print("   ");
			}
			if("createDate".equals(field)) {
				pw.print("   now()   ");
			}else {
				pw.print(String.format("#{%s}", field));
			}
			first = false;
		}
		pw.println(")");
		pw.println();
		return sw.toString();
	}

	private static String buildUpdate(String name, List<String> fields, Set<String> keys) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		
		//pw.print("#");
		//pw.println(keys);
		pw.println("update " + name);
		pw.println("<trim prefix='set' suffixOverrides=','>");
		for(String field : fields) {
			if(keys.contains(field)) {
				continue;
			}
			if("createDate".equals(field)) {
				continue;
			}
			if("updateDate".equals(field)) {
				pw.println(String.format("   <if test='true'>update_date=now()</if>", field));
				continue;
			}
			String var = String.format("#{%s}", field);
			pw.print(String.format("   <if test=' %s != null'>", field));
			pw.print(toUnderscore(field));
			pw.print("=");
			pw.print(var);
			pw.println("</if>");
		}
		pw.println("</trim>");
		boolean first = true;
		for(String key : keys) {
			if(first) {
				pw.print("where ");
			}else {
				pw.print("  and ");
			}
			pw.print(toUnderscore(key));
			pw.print("=");
			pw.println(String.format("#{%s}", key));
			first = false;
		}
		pw.println();
		return sw.toString();
	}

	private static String buildSelect(String name, Set<String> keys) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		pw.print("select * from " + name);
		boolean first = true;
		for(String key : keys) {
			if(first) {
				pw.print(" where ");
			}else {
				pw.print(" and ");
			}
			pw.print(toUnderscore(key));
			pw.print("=");
			pw.print(String.format("#{%s}", key));
			first = false;
		}
		pw.println();
		
		return sw.toString();
	}

	private static String buildDelete(String name, Set<String> keys) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);

		pw.print("delete from " + name);
		boolean first = true;
		for(String key : keys) {
			if(first) {
				pw.print(" where ");
			}else {
				pw.print(" and ");
			}
			pw.print(toUnderscore(key));
			pw.print("=");
			pw.print(String.format("#{%s}", key));
			first = false;
		}
		pw.println();
		
		return sw.toString();
	}
}
