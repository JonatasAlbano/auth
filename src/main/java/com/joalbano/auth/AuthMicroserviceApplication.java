package com.joalbano.auth;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.joalbano.auth.entity.Permission;
import com.joalbano.auth.entity.User;
import com.joalbano.auth.repository.PermissionRepository;
import com.joalbano.auth.repository.UserRepository;

@SpringBootApplication
public class AuthMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthMicroserviceApplication.class, args);
	}
	
	@Bean
	CommandLineRunner init(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		return args -> {
			initUsers(userRepository, permissionRepository, bCryptPasswordEncoder);
		};
	}
	
	private void initUsers(UserRepository userRepository, PermissionRepository permissionRepository,
			BCryptPasswordEncoder bCryptPasswordEncoder) {
		Permission permission = null;
		Permission adminPermission = permissionRepository.findByDescription("Admin");
		if(adminPermission == null) {
			permission = new Permission();
			permission.setDescription("Admin");
			permission = permissionRepository.save(permission);
		} else {
			permission = adminPermission;
		}
		
		User admin = new User();
		admin.setUserName("jonatas");
		admin.setAccountNonExpired(true);
		admin.setAccountNonLocked(true);
		admin.setAccountNonLocked(true);
		admin.setCredentialsNonExpired(true);
		admin.setEnabled(true);
		admin.setPassword(bCryptPasswordEncoder.encode("123456"));
		admin.setPermissions(Arrays.asList(permission));
		
		User getUserAdmin = userRepository.findByUserName("jonatas");
		if(getUserAdmin == null) {
			userRepository.save(admin);
		}
	}

}
