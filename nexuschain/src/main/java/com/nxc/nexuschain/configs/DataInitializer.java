package com.nxc.nexuschain.configs;

import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.enums.RoleEnum;
import com.nxc.nexuschain.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {

    private final UserRepository userRepository;

    public DataInitializer(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    public CommandLineRunner initializeData(PasswordEncoder passwordEncoder) {
        return args -> {
            // Check if admin user exists
            if (userRepository.findByUsername("admin").isEmpty()) {
                User adminUser = new User();
                adminUser.setUsername("admin");
                adminUser.setPassword(passwordEncoder.encode("123456"));
                adminUser.setFullName("Admin User");
                adminUser.setEmail("admin@example.com");
                adminUser.setRole(RoleEnum.ROLE_ADMIN);
                adminUser.setIsConfirm(true);
                adminUser.setIsDeleted(false);
                userRepository.save(adminUser);
                System.out.println("Admin user created");
            }
        };
    }
}