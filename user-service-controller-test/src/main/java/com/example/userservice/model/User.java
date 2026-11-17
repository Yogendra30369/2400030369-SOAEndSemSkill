package com.example.userservice.model;

public class User {
    private Long id;
    private String name;
    private String task;

    public User() {}

    public User(Long id, String name, String task) {
        this.id = id;
        this.name = name;
        this.task = task;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getTask() { return task; }
    public void setTask(String task) { this.task = task; }
}
