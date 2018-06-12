package com.spring.tests.liveCoding.controller;

import com.spring.tests.liveCoding.service.exampleService.ExampleService1;
import com.spring.tests.liveCoding.service.exampleService.ExampleService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExampleClass {

    private ExampleService1 exampleService1;
    private ExampleService2 exampleService2;

    @Autowired
    public ExampleClass(ExampleService1 exampleService1, ExampleService2 exampleService2) {
        this.exampleService1 = exampleService1;
        this.exampleService2 = exampleService2;
    }

    public ExampleClass() {
    }

    public String getCombinedMessage() {

        return exampleService1.getMessage() + exampleService2.getMessage();

    }
}
