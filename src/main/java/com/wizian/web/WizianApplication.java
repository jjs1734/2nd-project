package com.wizian.web;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.wizian.web.mapper")
@MapperScan("com.wizian.web.dao")
public class WizianApplication {

	public static void main(String[] args) {
		SpringApplication.run(WizianApplication.class, args);
	}

}
