package com.coaching.skilldevelopment.dto.rowmapper;

import com.coaching.skilldevelopment.dto.Todo;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
        Todo todo = new Todo();
        todo.setTodoId(rs.getInt("id"));
        todo.setName(rs.getString("name"));
        todo.setDescription(rs.getString("description"));
        todo.setCreatedOn(rs.getDate("created_on"));
        todo.setModifiedOn(rs.getDate("modified_on"));
        todo.setStatus(rs.getString("status"));
        return todo;
    }
}
