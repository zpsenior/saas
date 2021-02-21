package com.saas.school;

import java.io.PrintWriter;
import java.io.StringWriter;

import com.saas.training.AdminMutation;
import com.saas.training.AdminQuery;
import com.zpsenior.graphql4j.schema.Schema;
import com.zpsenior.graphql4j.utils.CRUDBuilder;
import com.zpsenior.graphql4j.utils.SQLBuilder;
import com.zpsenior.graphql4j.utils.SQLBuilder.Filter;

public class BuildCRUD implements Filter{

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

	public static void main(String[] args) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			doBuild(pw, AdminQuery.class, AdminMutation.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		pw.flush();
		System.out.println(sw.toString());
	}
	

	
	private static void doBuild(PrintWriter pw, Class<?> query, Class<?> mutation)throws Exception {
		Schema schema = new Schema(query, mutation);
		System.out.println(schema.toString());
		CRUDBuilder builder = new CRUDBuilder(schema);
		builder.build(pw, query, mutation, new BuildCRUD());
	}

	public boolean filterType(String name) {
		if(name.startsWith("Query") || name.startsWith("Mutation")) {
			return true;
		}
		return false;
	}

	public boolean filterField(String sqlType, String field) {
		if("insert".equals(sqlType) && "updateDate".equals(field)) {
			return true;
		}
		if("update".equals(sqlType) && "createDate".equals(field)) {
			return true;
		}
		return false;
	}

	@Override
	public String getInsertFieldValue(String field) {
		if("createDate".equals(field)) {
			return "   now()   ";
		}
		return null;
	}

	@Override
	public String getUpdateFieldValue(String field) {
		if("updateDate".equals(field)) {
			return String.format("   <if test='true'>update_date=now()</if>", field);
		}
		return null;
	}


}
