package com.lamov.service;

import lombok.ToString;

@ToString
public class CommandBody {
    private String description;
    private Priority priority;
    private String author;
    private String time;

    public void setDescription(String description) { this.description = description; }
    public String getDescription() { return this.description; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }

    public String getTime() { return time; }
    public void setTime(String time) { this.time = time; }
}