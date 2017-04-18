package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * In this program, a REST Web Service is created using Spring Boot
 * It reads a text file- vehicles.txt, and generates a report with the following information
 * 
 * 1. List of vehicles sorted by price
 * 2. List of average cost per car type sorted by price
 * 3. List of average cost per brand sorted by price
 * 4. List of average cost per color sorted by price
 * 5. List of average cost per engine type sorted by price
 *
 * The service returns a JSON response
 * 
 * 
 * @author Mohita Jethwani
 * 
 */


@Configuration
@EnableAutoConfiguration
@ComponentScan({ "controller", "service" })
public class BchProjApplication {

	/**
	 * The main program
	 * 
	 * @param args
	 *            command line arguments (ignored)
	 */
	public static void main(String[] args) {
		SpringApplication.run(BchProjApplication.class, args);
	}
}
