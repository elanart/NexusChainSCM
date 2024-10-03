package com.nxc.nexuschain.services;

import com.nxc.nexuschain.dtos.UserRequestDTO;
import com.nxc.nexuschain.dtos.UserUpdateRequestDTO;
import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.exceptions.DataNotFoundException;
import com.nxc.nexuschain.exceptions.InvalidParamException;

import java.util.Optional;

public interface UserService {
    User getUserById(Integer id);

    User createUser(UserRequestDTO userRequestDTO);

    User updateUser(Integer id, UserUpdateRequestDTO user);

    void deleteUserById(Integer id);

    void confirmUser(Integer id);

    String login(String username, String password) throws DataNotFoundException, InvalidParamException;

    Optional<User> getUserByUsername(String username);
}
