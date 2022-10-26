package com.fyp.highriseresbuildms.controller;

import com.fyp.highriseresbuildms.entity.JwtRequest;
import com.fyp.highriseresbuildms.entity.JwtResponse;
import com.fyp.highriseresbuildms.service.jwt.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class LoginController {

    @Autowired
    private JwtService jwtService;

    @RequestMapping(value = "/login",
            method = RequestMethod.POST)
    public JwtResponse createJwtToken(@RequestBody JwtRequest jwtRequest) throws Exception {
        return jwtService.createJwtToken(jwtRequest);
    }
}
