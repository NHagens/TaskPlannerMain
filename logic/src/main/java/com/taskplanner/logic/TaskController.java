package com.taskplanner.logic;

import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/task")
public class TaskController {
    private final ITaskRepository repository;

    public TaskController(ITaskRepository repository) {
        this.repository = repository;
    }

    @PostMapping(path = "/new")
    public @ResponseBody Task newTask(@Valid @RequestBody Task task) {
        return repository.save(task);
    }

    @GetMapping(path = "/findAll")
    public @ResponseBody Iterable<Task> findAllTasks() {
        return repository.findAll();
    }

    @GetMapping("/createRandom")
    public @ResponseBody List<Task> createRandom() {
        List<Task> foundTasks = new ArrayList<Task>();

        for(Task task : repository.findTasksWithTag("Random")) {
            foundTasks.add(task);
        }

        if(foundTasks.size() == 0) {
            return new ArrayList<Task>();
        }

        Random rand = new Random();
        ArrayList<Task> output = new ArrayList<Task>();
        output.add(foundTasks.get(rand.nextInt(foundTasks.size())));
        return output;
    }
}
