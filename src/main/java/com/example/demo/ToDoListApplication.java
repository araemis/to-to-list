package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ToDoListApplication {
  public static void main(String[] args) {
    System.setProperty("spring.devtools.restart.enabled", "true");

    SpringApplication.run(ToDoListApplication.class, args);

  }

}
