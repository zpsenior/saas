package com.saas.school;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import com.saas.training.AdminMutation;
import com.saas.training.AdminQuery;
import com.zpsenior.graphql4j.schema.Schema;
import com.zpsenior.graphql4j.utils.DBSchemaBuilder;
import com.zpsenior.graphql4j.utils.SQLBuilder;
import com.zpsenior.graphql4j.utils.SQLBuilder.Filter;

public class BuildSQL implements Filter {

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
		DBSchemaBuilder builder = new DBSchemaBuilder(schema);
		builder.build(pw, new BuildSQL());
	}

	public boolean filterType(String name) {
		if(name.startsWith("Query") || name.startsWith("Mutation")) {
			return true;
		}
		if(name.endsWith("Session")) {
			return true;
		}
		return false;
	}

	public String mapTableName(String name, String className) {
		name = SQLBuilder.toUnderscore(name);
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
	
	public String convertType(String type, int len) {
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
