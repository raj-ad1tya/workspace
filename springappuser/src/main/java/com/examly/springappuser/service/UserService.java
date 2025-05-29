package com.examly.springappuser.service;

import com.examly.springappuser.model.LoginDTO;
import com.examly.springappuser.model.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    User createUser(User user);
    LoginDTO loginUser(User user);

    // custom
    Boolean userExistsById(Integer userId);
}
