package com.Ape;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * @author Miao Xu
 *
 */
@SpringBootApplication(scanBasePackages = {"com.Ape"})
@MapperScan("com.Ape.dao")
public class App {
	
	public static void main(String[] args) throws Exception{
		
		SpringApplication.run(App.class, args);
	}

}
