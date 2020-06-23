package com.taskplanner.logic;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface IUserRepository extends CrudRepository<User, Integer> {
    @Query(value = "select user from User user where user.name = :username")
    User findByName(@Param("username") String username);
}
