package com.taskplanner.logic;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;

import javax.persistence.*;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class Tag {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;

    /*@ManyToMany(mappedBy = "tags")
    private List<Task> tasks;*/

    public Tag() {}

    public Tag(String name) {
        this.name = name;
    }
}
