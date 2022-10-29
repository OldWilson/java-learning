package org.example;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class Main extends WebMvcConfigurerAdapter {
    public static void main(String[] args) {
        System.out.println("Hello world!");
    }
}