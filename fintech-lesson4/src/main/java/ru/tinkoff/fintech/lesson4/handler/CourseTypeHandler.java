package ru.tinkoff.fintech.lesson4.handler;

import com.google.gson.Gson;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.json.JSONObject;
import ru.tinkoff.fintech.lesson4.model.Course;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseTypeHandler extends BaseTypeHandler<Course> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement, int i, Course course, JdbcType jdbcType) throws SQLException {
        preparedStatement.setString(i, toJson(course));
    }

    @Override
    public Course getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return fromJson(resultSet.getObject(s));
    }

    @Override
    public Course getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return resultSet.getObject(i, Course.class);
    }

    @Override
    public Course getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return callableStatement.getObject(i, Course.class);
    }

    private String toJson(Course course) {
        return new Gson().toJson(course);
    }

    private static Course fromJson(Object jsonObject) {
        JSONObject jsonObj = new JSONObject(new String((byte[]) jsonObject));
        return new Course(jsonObj.getString("name"), jsonObj.getString("description"));
    }
}
