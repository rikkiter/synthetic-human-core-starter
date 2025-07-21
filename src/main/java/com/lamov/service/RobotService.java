package com.lamov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.*;

@Service
public class RobotService {

    private DateTimeFormatter isoFormatter = DateTimeFormatter.ISO_TIME;

    ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2,
            4,
            1,
            TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(100)
    );

    @WeylandWatchingYou("console")
    public void giveCommand(
            String description,
            Priority priority,
            String author,
            String time
    ) {
        String message = "command: \"%s\" authored by \"%s\" at \"%s\" is done!\n";

        if(description.length() > 1000) throw new IllegalArgumentException("Command is too long (max 1000 characters)");

        if(!(priority instanceof Priority)) throw new IllegalArgumentException("Priority must be Enum");

        if(author.length() > 100) throw new IllegalArgumentException("Author name is too long (max 100 characters).");

        try {
            LocalTime.parse(time, isoFormatter);
        } catch (Exception e) {
            throw new IllegalArgumentException("Time must be in ISO-8601 format.");
        }

        if(priority == com.lamov.service.Priority.CRITICAL) {
            System.out.printf("CRITICAL " + message, description, author, time);
        }
        else if(priority == com.lamov.service.Priority.COMMON) {
            try {
                executor.execute(() -> {
                    System.out.printf("COMMON " + message, description, author, time);
                });
                System.out.println("Command added to queue.");
            } catch (Exception e) {
                throw new IllegalArgumentException("Queue is full.");
            }
        }
    }
}