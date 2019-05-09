package com.coursework.client.entity;

import java.util.Date;
import java.util.List;

public class TimeSpan {
    private Date date;
    private List<Segment> segments;
    private String id;
    private String _entityName;
    private String version;
    private Schedule schedule;

    public TimeSpan(String id, Date date, List<Segment> segments, String _entityName, String version) {
        this.date = date;
        this.segments = segments;
        this.id = id;
        this._entityName = _entityName;
        this.version = version;
    }

    public TimeSpan() {}

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Segment> getSegments() {
        return segments;
    }

    public void setSegments(List<Segment> segments) {
        this.segments = segments;
    }

    public String get_entityName() {
        return _entityName;
    }

    public void set_entityName(String _entityName) {
        this._entityName = _entityName;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
