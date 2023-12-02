package marketplace.ProjetJ2EE_SpringBoot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;


@SpringBootApplication
public class ProjetJ2EeSpringBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetJ2EeSpringBootApplication.class, args);
	}

	@RequestMapping("/error")
	public String handleError() {
		return "error";
	}

	public String getErrorPath() {
		return "/error";
	}

}
