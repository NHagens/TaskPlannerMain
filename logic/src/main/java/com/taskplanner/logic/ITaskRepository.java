package com.taskplanner.logic;

import org.springframework.data.repository.CrudRepository;

public interface ITaskRepository extends CrudRepository<Task, Integer> {

}
