package com.taskplanner.logic;

import lombok.Data;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
@Entity
public class Task {
    @Id
    @GeneratedValue
    private Integer id;

    private String name;
    private String description;
    private String startTime;
    private String endTime;

    @ManyToMany
    @JoinTable(
            name = "TASK_TAG",
            joinColumns = @JoinColumn(name = "TASK_ID"),
            inverseJoinColumns = @JoinColumn(name = "TAG_ID"))
    private List<Tag> tags;

    @ManyToOne
    private User postOwner;

    public Task() {}
    public Task(String name, String description, String startTime, String endTime) {
        this.name=name;
        this.description=description;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
