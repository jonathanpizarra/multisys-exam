package com.multisys.dishtansya.service.impl;

import com.multisys.dishtansya.exception.EmailExistsException;
import com.multisys.dishtansya.model.User;
import com.multisys.dishtansya.repository.UserRepository;
import com.multisys.dishtansya.req.RegisterReq;
import com.multisys.dishtansya.res.RegisterRes;
import com.multisys.dishtansya.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public RegisterRes registerUser(RegisterReq req) {
        User user = userRepository.findByEmail(req.getEmail());
        if(user != null){
            throw new EmailExistsException("Email already taken");
        }

        user = new User();
        user.setEmail(req.getEmail());
        user.setPassword(passwordEncoder.encode(req.getPassword()));

        User newUser = userRepository.save(user);
        System.out.println("new id: " + newUser.getId());
        RegisterRes res = new RegisterRes();
        res.setMessage("User successfully registered");
        return res;
    }


}
