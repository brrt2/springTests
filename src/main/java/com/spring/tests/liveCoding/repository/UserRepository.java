package com.spring.tests.liveCoding.repository;

import com.spring.tests.liveCoding.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {


    User findByUsername(String username);


}
