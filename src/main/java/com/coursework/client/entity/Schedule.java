package com.coursework.client.entity;

import java.util.List;

public class Schedule {
    private List<TimeSpan> timeSpans;
    private String id;
    private String _entityName;
    private String version;

    public Schedule(String id, List<TimeSpan> timeSpans, String _entityName, String version) {
        this.timeSpans = timeSpans;
        this.id = id;
        this._entityName = _entityName;
        this.version = version;
    }

    public Schedule() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<TimeSpan> getTimeSpans() {
        return timeSpans;
    }

    public void setTimeSpans(List<TimeSpan> timeSpans) {
        this.timeSpans = timeSpans;
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
