package com.nxc.nexuschain.services.impl;

import com.nxc.nexuschain.dto.user.request.UserRequest;
import com.nxc.nexuschain.entities.Account;
import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.repositories.UserRepository;
import com.nxc.nexuschain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUserAndAccount(UserRequest userRequest) {
        User user = User.builder()
                .fullName(userRequest.getFullName())
                .address(userRequest.getAddress())
                .phone(userRequest.getPhone())
                .avatar(userRequest.getAvatar())
                .email(userRequest.getEmail())
                .createdDate(LocalDateTime.now())
                .role(userRequest.getRole())
                .build();

        String hashedPassword = passwordEncoder.encode(userRequest.getAccount().getPassword());

        Account account = Account.builder()
                .username(userRequest.getAccount().getUsername())
                .password(hashedPassword)
                .user(user)
                .build();

        user.setAccount(account);
        return this.userRepository.save(user);
    }
}
