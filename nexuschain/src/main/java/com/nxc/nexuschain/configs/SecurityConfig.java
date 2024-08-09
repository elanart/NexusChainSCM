package com.nxc.nexuschain.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorizeRequests ->
                        authorizeRequests
                                .anyRequest().permitAll()
//                                .requestMatchers("/api/suppliers/register").permitAll()  // Cho phép truy cập không cần xác thực
//                                .anyRequest().authenticated()  // Các yêu cầu khác cần xác thực
                );
//                .formLogin(formLogin ->
//                        formLogin
//                                .loginPage("/login")  // Cấu hình trang đăng nhập nếu cần
//                                .permitAll()  // Cho phép tất cả người dùng truy cập trang đăng nhập
//                )
//                .logout(LogoutConfigurer::permitAll  // Cho phép tất cả người dùng thực hiện đăng xuất
//                );

        // Tắt CSRF nếu bạn không cần (thường là đối với API)
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
