package com.spring.tests.liveCoding.service;

import com.spring.tests.liveCoding.model.User;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    private List<User> listOfUsers = new ArrayList<>();


    public User addUser(User user) {

        if(listOfUsers.add(user)) {
            return user;
        } else return null;

    }

}
