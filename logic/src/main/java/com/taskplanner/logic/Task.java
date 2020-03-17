package com.taskplanner.logic;

public class Task {
    private final String name;
    private final String description;
    private final String startTime;
    private final String endTime;

    public Task(String name, String description, String startTime, String endTime) {
        this.name = name;
        this.description = description;

        this.startTime = startTime;
        this.endTime = endTime;
    }

    public String getName() {
        return name;
    }
    public String getDescription () {
        return description;
    }
    public String getStartTime() { return startTime; }
    public String getEndTime() { return endTime; }
}
