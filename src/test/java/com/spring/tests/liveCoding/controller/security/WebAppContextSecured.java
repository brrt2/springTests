package com.spring.tests.liveCoding.controller.security;


import com.spring.tests.liveCoding.model.Book;
import com.spring.tests.liveCoding.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
public class WebAppContextSecured {

    @Autowired
    private WebApplicationContext webContext;

    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Before
    public void setupMockMvc() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webContext)
                .apply(springSecurity())
                .build();
    }

    @Test
    public void shouldReturnHttpOkUponHittingThisEndpoint() throws Exception {

        List<Book> testList = new ArrayList<>();
        testList.add(new Book("R.Kipling","Jungle Book",1894));

        given(bookService.getAllBooks()).willReturn(testList);

//        this.mockMvc.perform(get("/sample/allBooks"))

        this.mockMvc.perform(get("/sample/allBooks")
                .with(user("a")
                        .password("b")
                        .roles("c")))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Jungle")));
    }

}