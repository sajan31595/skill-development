package com.coaching.skilldevelopment.payload;

import java.util.List;

public class CourseUsersRequest {

    private int courseId;
    private List<Integer> userIds;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public List<Integer> getUserIds() {
        return userIds;
    }

    public void setUserIds(List<Integer> userIds) {
        this.userIds = userIds;
    }
}
