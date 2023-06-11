package gb.library.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan({"gb.library.common"})
@EnableJpaRepositories("gb.library.auth.repositories")
public class LibraryAuthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryAuthServiceApplication.class, args);
	}

}
