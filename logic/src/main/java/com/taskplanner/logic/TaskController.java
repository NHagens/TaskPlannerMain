package com.taskplanner.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/task")
public class TaskController {
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }


    @CrossOrigin(origins = "http://localhost:3000")
    @PostMapping(path = "/new")
    public @ResponseBody Response newTask(@Valid @RequestBody Task task) {
        try {
            repository.save(task);
            return Response.ok().entity("Success").build();
        }
        catch (Exception ex) {
            return Response.ok().entity("Fail").build();
        }
    }
}
