package br.com.OrganizedDev.ProjectOrganizedDev;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjectOrganizedDevApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectOrganizedDevApplication.class, args);
	}

    @SpringBootApplication
    public static class LoginAuthApiApplication {

        public static void main(String[] args) {
            SpringApplication.run(LoginAuthApiApplication.class, args);
        }

    }
}
