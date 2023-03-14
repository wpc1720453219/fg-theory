package server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
// @Configuration
@ComponentScan(value = { "global", "listener", "filter", "controller", "service" })
public class Boot {
	public static void main(String[] args) {
		System.out.println("hello,world--->my sprint boot project");
		SpringApplication.run(Boot.class, args);		
	}
}
