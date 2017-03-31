package com.duotin.common.handler;

import com.duotin.common.EnumCode;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.lang.reflect.Method;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author lrh
 * @create 2014年12月23日下午2:03:48
 */
public abstract class AbstractEnumCodeHandler<T extends EnumCode> extends BaseTypeHandler {

	private Class<T> sourceClass;

	protected AbstractEnumCodeHandler(Class<T> sourceClass) {
		this.sourceClass = sourceClass;

	}

	@Override
	public void setNonNullParameter(PreparedStatement ps, int i, Object parameter, JdbcType jdbcType)
			throws SQLException {
		if (jdbcType == null) {
			jdbcType = JdbcType.OTHER;
		}
		// ps.setObject(i, ((EnumCode) parameter).code(), jdbcType.TYPE_CODE);
		ps.setString(i, ((EnumCode) parameter).code());
	}

	@Override
	public Object getNullableResult(ResultSet rs, String columnName) throws SQLException {
		return createEnumCode(rs.getObject(columnName));
	}

	private Object createEnumCode(Object val) {
		if (val != null) {
			String tmp = String.valueOf(val);
			if( val instanceof Boolean){
				 tmp = (boolean) val?"1":"0";
			}
			try {
				Method method = sourceClass.getDeclaredMethod("values");
				Object[] objs = (Object[]) method.invoke(sourceClass);
				for (Object o : objs) {
					EnumCode ec = (EnumCode) o;
					if (ec.code().equals(tmp)) {
						return ec;
					}
				}
			} catch (Exception e) {
			}
		}
		return null;
	}

	@Override
	public Object getNullableResult(CallableStatement cs, int columnIndex) throws SQLException {
		return createEnumCode(cs.getObject(columnIndex));
	}

}
