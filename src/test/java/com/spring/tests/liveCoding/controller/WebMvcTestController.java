package com.spring.tests.liveCoding.controller;

import com.spring.tests.liveCoding.model.Book;
import com.spring.tests.liveCoding.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(BookController.class)
public class WebMvcTestController {

    @MockBean
    private BookService bookService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHttpOkUponHittingThisEndpoint() throws Exception {


        List<Book> testList = new ArrayList<>();
        testList.add(new Book("R.Kipling","Jungle Book",1905));

        given(bookService.getAllBooks()).willReturn(testList);

        this.mockMvc.perform(get("/allBooks"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jungle")));

    }


}