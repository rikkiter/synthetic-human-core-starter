package com.lamov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.*;

@RestController
public class Controller {

    @Autowired
    RobotService robotService;

    @PostMapping("/give-command")
    public CommandBody giveCommand(@RequestBody CommandBody body) {
        robotService.giveCommand(body.getDescription(), body.getPriority(), body.getAuthor(), body.getTime());
        return body;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public String handleException(IllegalArgumentException exception) {
        return exception.getMessage();
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public String handleException2(HttpMessageNotReadableException exception) {
        return exception.getMessage();
    }
}

