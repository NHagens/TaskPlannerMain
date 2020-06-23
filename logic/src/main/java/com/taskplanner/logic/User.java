package com.taskplanner.logic;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    private String password;

    /*@OneToMany
    private List<Task> postedTasks;*/

    public User() {}

    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
}
