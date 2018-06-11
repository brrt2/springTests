package com.spring.tests.liveCoding.controller.basic;

import com.spring.tests.liveCoding.controller.ExampleClass;
import com.spring.tests.liveCoding.service.exampleService.ExampleService1;
import com.spring.tests.liveCoding.service.exampleService.ExampleService2;
import org.junit.Test;
import static org.assertj.core.api.Assertions.*;

public class FirstNonMvcTest {

    @Test
    public void shouldReturnCorrectCombinedMessage() {

        String EXPECTED_MESSAGE = "First part Second part";

        ExampleService1 exampleService1 = new ExampleService1();
        ExampleService2 exampleService2 = new ExampleService2();

        ExampleClass exampleClass = new ExampleClass(exampleService1,exampleService2);

        String obtainedMessage = exampleClass.getCombinedMessage();

        assertThat(obtainedMessage.equals(EXPECTED_MESSAGE));

    }





}
