package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.security.UserAuthDto;
import com.impoort.impoortapi.service.AuthenticationService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.Response;
import java.util.HashMap;

@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private final AuthenticationService service;

    @Autowired
    public UserAuthController(AuthenticationService service) {
        this.service = service;
    }
    
    @CrossOrigin
    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(HttpServletRequest httpServletRequest, @RequestBody final UserAuthDto userAuthDto){
        return new ResponseEntity<Object>(service.login(userAuthDto),HttpStatus.OK);
    }

    @CrossOrigin
    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDTO> addNewUser(@RequestBody UserRequestDTO userRequestDTO){
        return new ResponseEntity<UserResponseDTO>(service.signUp(userRequestDTO),HttpStatus.OK);
    }
}
