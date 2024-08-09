package com.nxc.nexuschain.services;

import com.nxc.nexuschain.dto.user.request.UserRequest;
import com.nxc.nexuschain.entities.User;

public interface UserService {
    User createUserAndAccount(UserRequest userRequest);
}
