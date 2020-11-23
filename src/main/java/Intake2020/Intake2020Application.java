package Intake2020;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Intake2020Application {

	public static void main(String[] args) {
		SpringApplication.run(Intake2020Application.class, args);
	}

}
