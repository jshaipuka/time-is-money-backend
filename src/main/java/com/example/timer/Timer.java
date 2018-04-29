package com.example.timer;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Timer {

    @Id
    private String token;

    private Long startTime;

    private String timeZone;

    private Long salary;

    private Long expirationTime;

    public Timer() {
    }

    Timer(String token, Long startTime, String timeZone, Long salary, Long expirationTime) {
        this.token = token;
        this.startTime = startTime;
        this.timeZone = timeZone;
        this.salary = salary;
        this.expirationTime = expirationTime;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getSalary() {
        return salary;
    }

    public void setSalary(Long salary) {
        this.salary = salary;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Long getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Long expirationTime) {
        this.expirationTime = expirationTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }
}
