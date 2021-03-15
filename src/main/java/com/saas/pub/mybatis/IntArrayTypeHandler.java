package com.saas.pub.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

@MappedTypes({int[].class})
public class IntArrayTypeHandler implements TypeHandler<int[]> {
	
	private final static String DELIMIT = ",";

	public IntArrayTypeHandler() {
	}

	private int[] convertTo(String columnValue) {
		String[] strs = columnValue.split(DELIMIT);
		int[] results = new int[strs.length];
		for(int i = 0; i < results.length; i++) {
			results[i] = Integer.parseInt(strs[i]);
		}
		return results;
	}

	public int[] getResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName); 
		if(columnValue == null){
			return null;
		}
		return convertTo(columnValue);
	}

	public int[] getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return convertTo(columnValue);
	}

	public int[] getResult(CallableStatement cs, int columnIndex)throws SQLException {
		String columnValue = cs.getString(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return convertTo(columnValue);
	}

	public void setParameter(PreparedStatement ps, int i, int[] parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			ps.setNull(i, Types.VARCHAR);
		else {
			StringBuffer sb = new StringBuffer();
			for(int value : parameter){
				if(sb.length() > 0){
					sb.append(DELIMIT);
				}
				sb.append(value);
			}
			ps.setString(i, sb.toString());
		}
	}

}
