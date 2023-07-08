package com.example.QlPhatTu;

import com.example.QlPhatTu.businessLogic.IUserService;
import com.example.QlPhatTu.model.Entity.Role;
import com.example.QlPhatTu.model.Entity.RoleName;
import com.example.QlPhatTu.model.Entity.User;
import com.example.QlPhatTu.persistence.IRoleRepository;
import com.example.QlPhatTu.persistence.IUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

@SpringBootApplication
public class QlPhatTuApplication {

	public static void main(String[] args) {
		SpringApplication.run(QlPhatTuApplication.class, args);
	}
	@Bean
	CommandLineRunner run(IUserService iUserService, IRoleRepository iRoleRepository,
						  IUserRepository iUserRepository, PasswordEncoder passwordEncoder) {
		return args -> {
			if (iUserRepository.findByEmail("admin@gmail.com").isEmpty()
					&& iUserRepository.findByEmail("superadminadmin@gmail.com").isEmpty()) {
				iUserService.saveRole(new Role(RoleName.USER));
				iUserService.saveRole(new Role(RoleName.ADMIN));
				iUserService.saveRole(new Role(RoleName.SUPERADMIN));
				iUserService.saverUser(new User("admin@gmail.com", passwordEncoder.encode("adminPassword"), new ArrayList<>()));
				iUserService.saverUser(new User("superadminadmin@gmail.com", passwordEncoder.encode("superadminPassword"), new ArrayList<>()));

				Role role = iRoleRepository.findByRoleName(RoleName.ADMIN);
				User user = iUserRepository.findByEmail("admin@gmail.com").orElse(null);
				user.getRoles().add(role);
				iUserService.saverUser(user);

				User userr = iUserRepository.findByEmail("superadminadmin@gmail.com").orElse(null);
				Role rolee = iRoleRepository.findByRoleName(RoleName.SUPERADMIN);
				userr.getRoles().add(rolee);
				iUserService.saverUser(userr);
			}
		};
	}


}
