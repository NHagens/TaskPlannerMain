package com.taskplanner.logic;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final ITaskRepository repository;

    public TaskController(ITaskRepository repository) {
        this.repository = repository;
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/new")
    public @ResponseBody Task newTask(@Valid @RequestBody Task task) {
        return repository.save(task);
    }

    @CrossOrigin(origins = "http://localhost:3000")
    @GetMapping(path = "/findAll")
    public @ResponseBody Iterable<Task> findAllTasks() {
        return repository.findAll();
    }
}
