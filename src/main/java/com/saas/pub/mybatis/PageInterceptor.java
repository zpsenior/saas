package com.saas.pub.mybatis;

import java.sql.Connection;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;
import org.apache.ibatis.executor.statement.RoutingStatementHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;

import com.saas.pub.QueryParam;

@Intercepts(    {
    @Signature(type = StatementHandler.class, method = "prepare", args = {StatementHandler.class, Connection.class}),
})
public class PageInterceptor implements Interceptor {

	@Override
	public Object intercept(Invocation invocation) throws Throwable {
		RoutingStatementHandler handler = (RoutingStatementHandler)invocation.getTarget();
		StatementHandler delegate =(StatementHandler) PropertyUtils.getProperty(handler, "delegate");;
		MappedStatement ms = (MappedStatement)PropertyUtils.getProperty(delegate, "mappedStatement");
		BoundSql boundSql = handler.getBoundSql();
        Object parameter = boundSql.getParameterObject();
        
		if((parameter instanceof QueryParam) && ms.getId().endsWith("byPage")) {
			String sql = boundSql.getSql();
			QueryParam page = (QueryParam) parameter;
			String pageSql = this.getPageSql(page, sql);
			BeanUtils.setProperty(boundSql, "sql", pageSql);
		}
		return invocation.proceed();
	}

	private String getPageSql(QueryParam page, String rawSql) {
		StringBuffer sql = new StringBuffer();
		sql.append(rawSql);
		String sortfield = page.getSortfield();
		if(page.isDescending()) {
			if(page.isForward() && page.getMin() > 0) {
				sql.append(" and ").append(sortfield).append("<").append(page.getMin());
			}else if(!page.isForward() && page.getMax() > 0){
				sql.append(" and ").append(sortfield).append(">").append(page.getMax());
			}
			sql.append(" order by ").append(sortfield).append(" desc limit 0, ").append(page.getPageSize());
		}else {
			if(page.isForward() && page.getMax() > 0) {
				sql.append(" and ").append(sortfield).append(">").append(page.getMax());
			}else if(!page.isForward() && page.getMin() > 0){
				sql.append(" and ").append(sortfield).append("<").append(page.getMin());
			}
			sql.append(" order by ").append(sortfield).append(" limit 0, ").append(page.getPageSize());
		}
		return sql.toString();
	}

}
