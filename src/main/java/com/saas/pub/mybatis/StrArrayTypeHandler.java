package com.saas.pub.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes({String[].class})
public class StrArrayTypeHandler implements TypeHandler<String[]> {
	
	private final static String DELIMIT = ",";

	public StrArrayTypeHandler() {
	}

	public String[] getResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName); 
		if(columnValue == null){
			return null;
		}
		return columnValue.split(DELIMIT);
	}

	public String[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return columnValue.split(DELIMIT);
	}

	public String[] getResult(CallableStatement cs, int columnIndex)throws SQLException {
		String columnValue = cs.getString(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return columnValue.split(DELIMIT);
	}

	public void setParameter(PreparedStatement ps, int i, String[] parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			ps.setNull(i, Types.NUMERIC);
		else {
			StringBuffer sb = new StringBuffer();
			for(String str : parameter){
				if(sb.length() > 0){
					sb.append(DELIMIT);
				}
				sb.append(str);
			}
			ps.setString(i, sb.toString());
		}
	}

}
