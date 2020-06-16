package com.taskplanner.logic;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tag")
public class TagController {
    ITagRepository repository;

    public TagController(ITagRepository repository) {
        this.repository = repository;
    }

    @EventListener
    public void appReady(ApplicationReadyEvent event) {
        List<Tag> baseTags = Arrays.asList(
                new Tag("Business"),
                new Tag("Hobby"),
                new Tag("Appointment"),
                new Tag("Random")
        );
        repository.saveAll(baseTags);
    }

    @GetMapping("/getAll")
    public @ResponseBody Iterable<Tag> getAll() {
        return repository.findAll();
    }
}
