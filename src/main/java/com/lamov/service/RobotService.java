package com.lamov.service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RobotService {
    DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_TIME;

    public void giveCommand(
            String description,
            Priority priority,
            String author,
            String time
    ) {
        if(description.length() > 1000) throw new IllegalArgumentException("Command is too long (max 1000 characters)");

        if(!(priority instanceof Priority)) throw new IllegalArgumentException("Priority must be Enum");

        if(author.length() > 100) throw new IllegalArgumentException("Author name is too long (max 100 characters).");

        try {
            LocalTime.parse(time, isoFormatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Time must be in ISO-8601 format.");
        }

        if(priority == Priority.CRITICAL) {
            System.out.printf("CRITICAL command: \"%s\" authored by \"%s\" at time is done!", description, author, time);
        }

        return;
    }

    public static void main(String[] args) {
        RobotService robotService = new RobotService();
        robotService.giveCommand("Hello", Priority.CRITICAL, "Slava", "18:00:00");
    }
}


enum Priority {
    CRITICAL,
    COMMON
}
