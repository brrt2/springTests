package com.spring.tests.liveCoding.controller;


import com.spring.tests.liveCoding.model.Book;
import com.spring.tests.liveCoding.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
public class StandaloneSetupControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
    }


    @Test
    public void shouldReturnHttpOkUponHittingThisEndpoint() throws Exception {


        List<Book> testList = new ArrayList<>();
        testList.add(new Book("R.Kipling","Jungle Book",1905));

        given(bookService.getAllBooks()).willReturn(testList);

//        this.mockMvc.perform(get("/allBooks"))
//                .andExpect(status().isOk())
//                .andExpect(content().string(containsString("Jungle")));

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/allBooks").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();


        String expected = "[{\"author\":\"R.Kipling\",\"title\":\"Jungle Book\",\"yearPublished\":1905}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);

    }


}