package com.coaching.skilldevelopment.dto;

import java.util.Date;

public class Todo {
    public enum TODO_STATUS{CREATED, COMPLETED}
    private int todoId;
    private String todoName;
    private String description;
    private String status;
    private Date createdOn;
    private Date modifiedOn;
    private int userId;

    public int getTodoId() {
        return todoId;
    }

    public void setTodoId(int todoId) {
        this.todoId = todoId;
    }

    public String getTodoName() {
        return todoName;
    }

    public void setTodoName(String todoName) {
        this.todoName = todoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        TODO_STATUS result = null;
        for (TODO_STATUS todo_status : TODO_STATUS.values()) {
            if (todo_status.name().equalsIgnoreCase(status)) {
                this.status = todo_status.name().substring(0, 2);
                break;
            }
        }
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
