package com.login.loginspring;

import com.login.loginspring.domain.Role;
import com.login.loginspring.domain.User;
import com.login.loginspring.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class LoginSpringApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginSpringApplication.class, args);
	}

	@Bean
	PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

	/*@Bean
	CommandLineRunner run(UserService userService){
		return args -> {
			userService.saveRole(new Role(null, "ROLE_USER"));
			userService.saveRole(new Role(null, "ROLE_ADMIN"));

			userService.saveUser(new User(null, "Isabela Cristina", "isabela", "1234", new ArrayList<>()));
			userService.addRouleToUser("isabela", "ROLE_ADMIN");
			userService.addRouleToUser("isabela", "ROLE_USER");

			userService.saveUser(new User(null, "Maria", "maria", "1234", new ArrayList<>()));
			userService.addRouleToUser("maria", "ROLE_USER");

			userService.saveUser(new User(null, "Joao", "joao", "1234", new ArrayList<>()));
			userService.addRouleToUser("joao", "ROLE_USER");


			//userService.saveRole(new Role(null, "ROLE_MANAGER"));
			//userService.saveRole(new Role(null, "ROLE_SUPER_ADMIN"));
			//userService.saveUser(new User(null, "Will Smith", "will", "1234", new ArrayList<>()));
			//userService.saveUser(new User(null, "Jim Carry", "jim", "1234", new ArrayList<>()));
			//userService.saveUser(new User(null, "Arnold S", "arnold", "1234", new ArrayList<>()));
			//userService.addRouleToUser("isabela", "ROLE_USER");
			//userService.addRouleToUser("john", "ROLE_MANAGER");
			//userService.addRouleToUser("will", "ROLE_MANAGER");
			//userService.addRouleToUser("jim", "ROLE_ADMIN");
			//userService.addRouleToUser("arnold", "ROLE_SUPER_ADMIN");
			//userService.addRouleToUser("arnold", "ROLE_ADMIN");
			//userService.addRouleToUser("arnold", "ROLE_USER");
		};
	}*/
}
