package com.example.demo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class HelloServiceTest {

    @Test
    void test() {
        HelloService helloService = new HelloService();
        assertThat(helloService).isNotNull();
        System.out.println("test");
    }

}