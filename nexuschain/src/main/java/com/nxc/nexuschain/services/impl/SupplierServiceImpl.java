package com.nxc.nexuschain.services.impl;

import com.nxc.nexuschain.dto.supplier.request.SupplierRegistrationRequest;
import com.nxc.nexuschain.dto.supplier.response.SupplierRegistrationResponse;
import com.nxc.nexuschain.entities.Account;
import com.nxc.nexuschain.entities.Supplier;
import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.enums.RoleEnum;
import com.nxc.nexuschain.repositories.SupplierRepository;
import com.nxc.nexuschain.repositories.UserRepository;
import com.nxc.nexuschain.services.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final UserRepository userRepository;
    private final SupplierRepository supplierRepository;

    @Override
    public SupplierRegistrationResponse registerSupplier(SupplierRegistrationRequest request) {
        User user = User.builder()
                .fullName(request.getFullName())
                .address(request.getAddress())
                .phone(request.getPhone())
                .avatar(request.getAvatar())
                .email(request.getEmail())
                .createdDate(LocalDateTime.now())
                .role(RoleEnum.ROLE_SUPPLIER)
                .build();

        Account account = Account.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .user(user)
                .build();

        user.setAccount(account);
        this.userRepository.save(user);

        Supplier supplier = Supplier.builder()
                .paymentTerms(request.getPaymentTerms())
                .user(user)
                .build();

        this.supplierRepository.save(supplier);

        return SupplierRegistrationResponse.builder()
                .id(user.getId())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .isConfirmed(user.getIsConfirm())
                .createdDate(user.getCreatedDate())
                .build();
    }
}
