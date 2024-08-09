package com.nxc.nexuschain.repositories;

import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.enums.RoleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    List<User> findByRole(RoleEnum role);
}
