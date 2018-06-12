package com.spring.tests.liveCoding.controller.basic;

import com.spring.tests.liveCoding.LiveCodingApplication;
import com.spring.tests.liveCoding.controller.ExampleClass;
import com.spring.tests.liveCoding.service.exampleService.ExampleService1;
import com.spring.tests.liveCoding.service.exampleService.ExampleService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = LiveCodingApplication.class)
public class FirstNonMvcTest {

    @Autowired
    private ExampleService1 exampleService1;

    @Autowired
    private ExampleService2 exampleService2;

    @Test
    public void shouldReturnCorrectCombinedMessage() {

        String EXPECTED_MESSAGE = "First part Second part";

        ExampleClass exampleClass = new ExampleClass(exampleService1,exampleService2);

        String obtainedMessage = exampleClass.getCombinedMessage();

        assertThat(obtainedMessage.equals(EXPECTED_MESSAGE));

    }

}
