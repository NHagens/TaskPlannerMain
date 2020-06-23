package com.taskplanner.logic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ITaskRepository extends CrudRepository<Task, Integer> {
    @Query(value = "select task from Task task join task.tags tags where tags.name = :tag")
    Iterable<Task> findTasksWithTag(@Param("tag") String tag);

    @Query(value = "select task from Task task inner join task.postOwner user where  user.name = :username")
    Iterable<Task> findAllFromUser(@Param("username") String username);
}
