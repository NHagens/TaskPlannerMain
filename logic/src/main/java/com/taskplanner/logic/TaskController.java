package com.taskplanner.logic;

import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/task")
public class TaskController {
    @GetMapping("/new")
    @ResponseBody
    public Task newTask(@RequestParam(required=true) String name, @RequestParam(required=true) String description, @RequestParam(required=true) String startTime, @RequestParam(required=true) String endTime) {
        return new Task(name, description, startTime, endTime);
    }
}
