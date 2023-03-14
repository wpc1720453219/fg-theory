package org.ruhr.architecture;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
@ComponentScan(value = { "org.ruhr.architecture" })
public class Boot {

	public static void main(String[] args) {

		SpringApplication.run(Boot.class, args);

	}

}