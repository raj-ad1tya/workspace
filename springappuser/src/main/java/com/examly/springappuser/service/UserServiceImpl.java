package com.examly.springappuser.service;

import com.examly.springappuser.config.CustomUserDetails;
import com.examly.springappuser.config.JwtUtils;
import com.examly.springappuser.model.LoginDTO;
import com.examly.springappuser.model.User;
import com.examly.springappuser.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public User createUser(User user) {
        try {
            return userRepo.save(user);
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public LoginDTO loginUser(User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );

        if (authentication.isAuthenticated()) {
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
            String token = jwtUtils.generateToken(userDetails);

            return new LoginDTO(token);
        }

        return null;
    }

    @Override
    public Boolean userExistsById(Integer userId) {
        return userRepo.existsById(userId);
    }
}