package com.multisys.dishtansya.controller;

import com.multisys.dishtansya.model.User;
import com.multisys.dishtansya.req.RegisterReq;
import com.multisys.dishtansya.res.RegisterRes;
import com.multisys.dishtansya.service.UserService;
import com.multisys.dishtansya.service.impl.UserDetailsServiceImpl;
import com.multisys.dishtansya.service.impl.UserServiceImpl;
import com.multisys.dishtansya.util.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class UserController {

    UserServiceImpl userService;

    UserDetailsServiceImpl userDetailsService;

    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;

    @Autowired
    public UserController(UserServiceImpl userService, AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService){
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @PostMapping("/register")
    @Operation(description = "register new user")
    public ResponseEntity<RegisterRes> registerUser( @ModelAttribute RegisterReq req){
        RegisterRes res = userService.registerUser(req);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    @Operation(description = "login user")
    public ResponseEntity<Map<String, String>> loginUser(@ModelAttribute RegisterReq req) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(req.getEmail(), req.getPassword())
            );
        } catch (Exception e) {
            Map<String, String> res = new HashMap<>();
            res.put("message", "Invalid credentials");

            return new ResponseEntity<>(res, HttpStatus.CREATED);
        }

        final UserDetails userDetails = userDetailsService.loadUserByUsername(req.getEmail());

        final String jwt = jwtUtil.generateToken(userDetails);

        Map<String, String> res = new HashMap<>();
        res.put("access_token", jwt);

        return new ResponseEntity<>(res, HttpStatus.CREATED);

    }
}
