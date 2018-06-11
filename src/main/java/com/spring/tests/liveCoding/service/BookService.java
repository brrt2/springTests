package com.spring.tests.liveCoding.service;

import com.spring.tests.liveCoding.model.Book;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {

    private List<Book> listOfBooks = new ArrayList<>();

    public List<Book> getAllBooks() {

        return listOfBooks;

    }

}
