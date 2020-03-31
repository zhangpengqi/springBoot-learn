package com.example.task1044;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.example.task1044.mapper")
public class Task1044Application {

	public static void main(String[] args) {
		SpringApplication.run(Task1044Application.class, args);
	}

}
