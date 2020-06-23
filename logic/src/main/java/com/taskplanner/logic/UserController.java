package com.taskplanner.logic;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {
    private final IUserRepository repository;

    public UserController(IUserRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        List<User> baseUsers = Arrays.asList(
                new User("Test1"),
                new User("Test2")
        );
        repository.saveAll(baseUsers);
    }

    @GetMapping("/getUser")
    public User getUser(@Valid @RequestParam("username") String name) {
        return repository.findByName(name);
    }

    @GetMapping("/getAll")
    public Iterable<User> getAllUsers() {
        return repository.findAll();
    }
}
