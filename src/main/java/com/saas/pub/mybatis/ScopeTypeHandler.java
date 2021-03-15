package com.saas.pub.mybatis;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.MappedTypes;
import org.apache.ibatis.type.TypeHandler;

import com.saas.pub.Scope;

@MappedTypes({Scope.class})
public class ScopeTypeHandler implements TypeHandler<Scope> {

	@Override
	public void setParameter(PreparedStatement ps, int i, Scope parameter, JdbcType jdbcType) throws SQLException {
		if (parameter == null)
			ps.setNull(i, Types.VARCHAR);
		else {
			ps.setString(i, parameter.toString());
		}
	}

	@Override
	public Scope getResult(ResultSet rs, String columnName) throws SQLException {
		String columnValue = rs.getString(columnName); 
		if(columnValue == null){
			return null;
		}
		return new Scope(columnValue);
	}

	@Override
	public Scope getResult(ResultSet rs, int columnIndex) throws SQLException {
		String columnValue = rs.getString(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return new Scope(columnValue);
	}

	@Override
	public Scope getResult(CallableStatement cs, int columnIndex) throws SQLException {
		String columnValue = cs.getString(columnIndex); 
		if(columnValue == null){
			return null;
		}
		return new Scope(columnValue);
	}

}
