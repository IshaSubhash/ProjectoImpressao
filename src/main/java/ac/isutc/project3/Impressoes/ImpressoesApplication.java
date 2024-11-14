package ac.isutc.project3.Impressoes;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ImpressoesApplication {
	public static void main(String[] args) {
		SpringApplication.run(ImpressoesApplication.class, args);
	}
}
