package dodardmathieu.cgmexercise;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("dodardmathieu.cgmexercise.*")
@EnableJpaRepositories
@EntityScan("dodardmathieu.cgmexercise.*")
@SpringBootApplication
public class DodardCgmExerciserApplication {
	
	
	public static void main(String[] args) {
		SpringApplication.run(DodardCgmExerciserApplication.class, args);
	}

}
