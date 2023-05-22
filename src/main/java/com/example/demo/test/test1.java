package com.example.demo.test;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;

import com.example.demo.TpRecetas2023Application;

public class test1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SpringApplication.run(TpRecetas2023Application.class, args);
	}
	@Bean
	CommandLineRunner commandLineRunner() {
			return args-> {
				
	
				
				
				System.out.println("");
				System.out.println("");
				System.out.println("             corriendo test1");
				System.out.println("");
				System.out.println("");
			};
	}

}
