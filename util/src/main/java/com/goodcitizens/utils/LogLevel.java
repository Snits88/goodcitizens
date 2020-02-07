package com.goodcitizens.utils;

public enum LogLevel {

    CONFIGURATION("Configuration Level"),
    RESOURCE("Resource Level"),
    BUSINESS("Resource Business"),
    PERSISTENSE("Resource Persistence");

    private String level;

    LogLevel(String value) {
        setLevel(value);
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }
}
