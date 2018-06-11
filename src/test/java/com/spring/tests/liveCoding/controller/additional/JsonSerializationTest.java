package com.spring.tests.liveCoding.controller.additional;


import com.spring.tests.liveCoding.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.JsonTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@JsonTest
public class JsonSerializationTest {

    @Autowired
    private JacksonTester<Book> json;

    @Test
    public void serializeJson() throws IOException {
        Book book = new Book(
                "A.Smith", "SampleTitle",1805);

        assertThat(this.json.write(book))
                .extractingJsonPathStringValue("@.author")
                .isEqualTo("A.Smith");
    }

}
