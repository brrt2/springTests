package com.spring.tests.liveCoding.controller.basic;

import com.spring.tests.liveCoding.controller.SampleController;
import com.spring.tests.liveCoding.model.Book;
import com.spring.tests.liveCoding.service.BookService;
import com.spring.tests.liveCoding.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SampleController.class,secure = false)
public class WebMvcTestController {

    @MockBean
    private BookService bookService;

    @MockBean
    private UserService userService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnHttpOkUponHittingThisEndpoint() throws Exception {
        
        List<Book> testList = new ArrayList<>();
        testList.add(new Book("R.Kipling","Jungle Book",1894));

        given(bookService.getAllBooks()).willReturn(testList);

        this.mockMvc.perform(get("/sample/allBooks"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jungle")));
    }

    @Test
    public void shouldReturnHttp4xxAsWrongURLisProvided() throws Exception {

        List<Book> testList = new ArrayList<>();
        testList.add(new Book("R.Kipling", "Jungle Book", 1894));

        given(bookService.getAllBooks()).willReturn(testList);

        this.mockMvc.perform(get("/false"))
                .andExpect(status().is4xxClientError());
    }

}