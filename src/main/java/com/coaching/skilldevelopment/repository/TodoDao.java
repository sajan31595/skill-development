package com.coaching.skilldevelopment.repository;

import com.coaching.skilldevelopment.dto.Todo;
import com.coaching.skilldevelopment.dto.rowmapper.TodoRowMapper;
import com.coaching.skilldevelopment.repository.interfaces.ITodoDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.*;
import java.util.List;

@Repository
public class TodoDao implements ITodoDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Transactional(readOnly=true)
    public List<Todo> getTodos(int userId) {
        String sql = "select * from todos where user_id=" + userId;
        List<Todo> todos = null;
        try{
            todos = jdbcTemplate.query(sql, new TodoRowMapper());
        }catch (Exception ignored){}
        return todos;
    }

    @Override
    @Transactional(readOnly=true)
    public Todo getTodo(int todoId) {
        Todo todo = null;
        try{
            todo = jdbcTemplate.queryForObject(
                    "select * from todos where id=?",
                    new Object[]{todoId}, new int[]{Types.INTEGER}, new TodoRowMapper());
        }catch (Exception ex){}
        return todo;
    }

    @Override
    @Transactional
    public void createTodo(Todo todo) {
        final String sql = "INSERT INTO todos(ID, NAME, DESCRIPTION, CREATED_ON, MODIFIED_ON, STATUS, USER_ID )" +
                " VALUES (nextval('todos_seq'), ?, ?, ?, ?, ?, ?)";
        try {
            KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                    PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    ps.setString(1, todo.getTodoName());
                    ps.setString(2, todo.getDescription());
                    ps.setDate(3, new java.sql.Date(todo.getCreatedOn().getTime()));
                    ps.setDate(4, new java.sql.Date(todo.getModifiedOn().getTime()));
                    ps.setString(5, todo.getStatus());
                    ps.setInt(6, todo.getUserId());
                    return ps;
                }
            }, holder);
        }
        catch(Exception ex) {
        }
    }

    @Override
    @Transactional
    public void updateTodo(int todoId, Todo todo) {
        String sql = "UPDATE todos SET name=?, description=?, modified_on=CURRENT_DATE, status=? WHERE id=?";
        try{
            jdbcTemplate.update(sql,
                    todo.getTodoName(),
                    todo.getDescription(),
                    todo.getStatus(),
                    todoId);
        } catch (Exception ex){
        }
    }

    @Override
    @Transactional
    public void deleteTodo(int todoId) {
        try{
            jdbcTemplate.update("DELETE FROM todos WHERE id=?", todoId);
        }catch (Exception ex){ }
    }
}
