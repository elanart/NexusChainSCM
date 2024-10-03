package com.nxc.nexuschain.components;

import com.nxc.nexuschain.entities.User;
import com.nxc.nexuschain.exceptions.InvalidParamException;
import org.springframework.security.core.userdetails.UserDetails;

public interface JwtTokenUtilInterface {
    String generateToken(User user) throws InvalidParamException;

    boolean validateToken(String token, UserDetails userDetails);

    String extractUsername(String token);
}
