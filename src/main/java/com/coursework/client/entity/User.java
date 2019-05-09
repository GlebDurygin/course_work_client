package com.coursework.client.entity;

public class User {
    private String name;
    private String lastName;
    private String ip;
    private Schedule schedule;
    private String id;
    private String _entityName;
    private String version;

    public User( String id,String name, String lastName, String ip, Schedule schedule, String _entityName, String version) {
        this.name = name;
        this.lastName = lastName;
        this.ip = ip;
        this.schedule = schedule;
        this.id = id;
        this._entityName = _entityName;
        this.version = version;
    }

    public User(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
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
