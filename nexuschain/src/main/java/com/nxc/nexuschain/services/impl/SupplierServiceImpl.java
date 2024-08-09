package com.nxc.nexuschain.services.impl;

import com.nxc.nexuschain.dto.supplier.request.SupplierRegistrationRequest;
import com.nxc.nexuschain.dto.supplier.response.SupplierRegistrationResponse;
import com.nxc.nexuschain.dto.user.response.UserResponse;
import com.nxc.nexuschain.entities.Supplier;
import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.repositories.SupplierRepository;
import com.nxc.nexuschain.services.SupplierService;
import com.nxc.nexuschain.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final UserService userService;
    private final SupplierRepository supplierRepository;

    @Override
    public SupplierRegistrationResponse registerSupplier(SupplierRegistrationRequest request) {
        User user = this.userService.createUserAndAccount(request.getUser());

        Supplier supplier = Supplier.builder()
                .paymentTerms(request.getPaymentTerms())
                .user(user)
                .build();

        this.supplierRepository.save(supplier);

        UserResponse userResponse = UserResponse.builder()
                .fullName(user.getFullName())
                .email(user.getEmail())
                .address(user.getAddress())
                .phone(user.getPhone())
                .avatar(user.getAvatar())
                .isConfirmed(user.getIsConfirm())
                .createdDate(user.getCreatedDate())
                .build();

        return SupplierRegistrationResponse.builder()
                .user(userResponse)
                .build();
    }
}
