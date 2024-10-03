package com.nxc.nexuschain.services.impl;

import com.nxc.nexuschain.components.JwtTokenUtilInterface;
import com.nxc.nexuschain.dtos.UserRequestDTO;
import com.nxc.nexuschain.dtos.UserUpdateRequestDTO;
import com.nxc.nexuschain.entities.Carrier;
import com.nxc.nexuschain.entities.Supplier;
import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.enums.RoleEnum;
import com.nxc.nexuschain.exceptions.DataNotFoundException;
import com.nxc.nexuschain.exceptions.InvalidParamException;
import com.nxc.nexuschain.repositories.CarrierRepository;
import com.nxc.nexuschain.repositories.SupplierRepository;
import com.nxc.nexuschain.repositories.UserRepository;
import com.nxc.nexuschain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;
    private final CarrierRepository carrierRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtilInterface jwtTokenUtil;

    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
    }

    @Override
    public User createUser(UserRequestDTO userRequestDTO) {
        User newUser = User.builder()
                .username(userRequestDTO.getUsername())
                .password(userRequestDTO.getPassword())
                .fullName(userRequestDTO.getFullName())
                .address(userRequestDTO.getAddress())
                .phone(userRequestDTO.getPhone())
                .email(userRequestDTO.getEmail())
                .role(userRequestDTO.getRole())
                .isConfirm(false)
                .isDeleted(false)
                .createdDate(LocalDateTime.now())
                .build();

        if (newUser.getRole() == RoleEnum.ROLE_ADMIN) {
            newUser.setIsConfirm(true);
        }

        if (newUser.getRole() == RoleEnum.ROLE_SUPPLIER) {
            Supplier supplier = Supplier.builder()
                    .paymentTerms(userRequestDTO.getPaymentTerms())
                    .user(newUser)
                    .build();
            supplierRepository.save(supplier);
        }

        if (newUser.getRole() == RoleEnum.ROLE_CARRIER) {
            Carrier carrier = Carrier.builder()
                    .cooperationTerms(userRequestDTO.getCooperationTerms())
                    .user(newUser)
                    .build();
            carrierRepository.save(carrier);
        }

        return userRepository.save(newUser);
    }

    @Override
    public String login(String username, String password) throws DataNotFoundException, InvalidParamException {
        Optional<User> optionalUser = userRepository.findByUsername(username);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Invalid username or password!");
        }
        User existingUser = optionalUser.get();
        // check password

        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new BadCredentialsException("Invalid username or password!");
        }

        return jwtTokenUtil.generateToken(existingUser);
    }

    @Override
    public User updateUser(Integer id, UserUpdateRequestDTO user) {
        User existingUser = getUserById(id);
        existingUser.setFullName(user.getFullName());
        existingUser.setEmail(user.getEmail());
        existingUser.setUsername(user.getUsername());
        existingUser.setPassword(user.getPassword());

        existingUser.setRole(user.getRole());

        userRepository.save(existingUser);

        if (existingUser.getRole() == RoleEnum.ROLE_SUPPLIER) {
            Supplier updateSupplier = Supplier.builder()
                    .paymentTerms(user.getPaymentTerms())
                    .user(existingUser).build();

            supplierRepository.save(updateSupplier);
        }

        if (existingUser.getRole() == RoleEnum.ROLE_CARRIER) {
            Carrier carrier = Carrier.builder()
                    .cooperationTerms(user.getCooperationTerms())
                    .user(existingUser)
                    .build();
            carrierRepository.save(carrier);
        }

        return existingUser;
    }

    @Override
    public void deleteUserById(Integer id) {
        User user = getUserById(id);
        user.setIsDeleted(true);
        userRepository.save(user);
    }

    @Override
    public void confirmUser(Integer id) {
        User user = getUserById(id);
        user.setIsConfirm(true);
        userRepository.save(user);
    }

    @Override
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
