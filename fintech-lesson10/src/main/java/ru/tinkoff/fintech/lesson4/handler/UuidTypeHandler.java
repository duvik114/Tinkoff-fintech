package ru.tinkoff.fintech.lesson4.handler;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

public class UuidTypeHandler extends BaseTypeHandler<UUID> {

    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, UUID uuid, JdbcType jdbcType
    ) throws SQLException {
        preparedStatement.setString(i, uuid.toString());
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return resultSet.getObject(s, UUID.class);
    }

    @Override
    public UUID getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getObject(i, UUID.class);
    }

    @Override
    public UUID getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i, UUID.class);
    }
}
