package com.coaching.skilldevelopment.dto.rowmapper;

import com.coaching.skilldevelopment.dto.Event;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EventRowMapper implements RowMapper<Event> {

    @Override
    public Event mapRow(ResultSet rs, int rowNum) throws SQLException {
        Event event = new Event();
        event.setEventId(rs.getInt("id"));
        event.setCourseId(rs.getInt("course_id"));
        event.setEventType(rs.getString("event_type"));
        event.setEventName(rs.getString("event_name"));
        event.setEventDescription(rs.getString("event_description"));
        event.setEventDate(rs.getDate("event_date"));
        return event;
    }
}
