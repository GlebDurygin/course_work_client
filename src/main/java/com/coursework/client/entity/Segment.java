package com.coursework.client.entity;

import java.sql.Time;

public class Segment {
    private Time start;
    private Time end;
    private Long duration;
    private String id;
    private String _entityName;
    private Integer version;
    private TimeSpan timeSpan;

    public Segment(String _entityName, String id, Time start, Integer version, Long duration, Time end) {
        this.id = id;
        this.start = start;
        this.end = end;
        this.duration = duration;
        this._entityName = _entityName;
        this.version = version;
    }

    public Segment() {}

    public TimeSpan getTimeSpan() {
        return timeSpan;
    }

    public void setTimeSpan(TimeSpan timeSpan) {
        this.timeSpan = timeSpan;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Time getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Time getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public String get_entityName() {
        return _entityName;
    }

    public void set_entityName(String _entityName) {
        this._entityName = _entityName;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
