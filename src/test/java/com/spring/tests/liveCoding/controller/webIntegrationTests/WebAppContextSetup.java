package com.spring.tests.liveCoding.controller.webIntegrationTests;

import com.spring.tests.liveCoding.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebAppContextSetup {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void exampleTest() {
        Book book = this.restTemplate.getForObject("/sample/oneBook", Book.class);
        assertThat(book.getAuthor()).isEqualTo("R.Kipling");
    }

}
