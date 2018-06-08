package com.spring.tests.liveCoding.controller;


import com.spring.tests.liveCoding.model.Book;
import com.spring.tests.liveCoding.model.User;
import com.spring.tests.liveCoding.service.BookService;
import com.spring.tests.liveCoding.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;


    @GetMapping(value = "/allBooks")
    public List<Book> getAllBooks () {

     return bookService.getAllBooks();

    }

    @PostMapping("/addUser")
    public ResponseEntity<Void> addUser (@RequestBody User user) {

        User addedUser = userService.addUser(user);

        if (addedUser == null)
            return ResponseEntity.noContent().build();

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(
                "/{id}").buildAndExpand(addedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

}
