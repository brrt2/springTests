package com.spring.tests.liveCoding.controller.basic;

import com.spring.tests.liveCoding.controller.SampleController;
import com.spring.tests.liveCoding.model.User;
import com.spring.tests.liveCoding.service.BookService;
import com.spring.tests.liveCoding.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(value = SampleController.class, secure = false)
public class ControllerPostMethodTest {

    @MockBean
    private UserService userService;

    @MockBean
    private BookService bookServiceService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldHaveCreatedStatus() throws Exception {

        User mockUser = new User("sampleUser");

        String userJson = "{\"username\":\"sampleUser\"}";

        Mockito.when(userService.addUser(Mockito.any(User.class))).thenReturn(mockUser);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/sample/addUser")
                .accept(MediaType.APPLICATION_JSON)
                .content(userJson)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();

        MockHttpServletResponse response = result.getResponse();

        assertEquals(HttpStatus.CREATED.value(), response.getStatus());

    }

}
