package com.spring.tests.liveCoding.controller.basic;

import com.spring.tests.liveCoding.controller.SampleController;
import com.spring.tests.liveCoding.model.Book;
import com.spring.tests.liveCoding.service.BookService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
public class StandaloneSetupControllerTest {

    private MockMvc mockMvc;

    @Mock
    private BookService bookService;

    @InjectMocks
    private SampleController sampleController;

    @Before
    public void setUp() {

        mockMvc = MockMvcBuilders.standaloneSetup(sampleController).build();
    }


    @Test
    public void shouldReturnHttpOkUponHittingThisEndpoint() throws Exception {

        List<Book> testList = new ArrayList<>();
        testList.add(new Book("R.Kipling","Jungle Book",1894));

        given(bookService.getAllBooks()).willReturn(testList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/sample/allBooks").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        String expected = "[{\"author\":\"R.Kipling\",\"title\":\"Jungle Book\",\"yearPublished\":1894}]";

        JSONAssert.assertEquals(expected, result.getResponse()
                .getContentAsString(), false);
    }


}