package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.security.authDto.UserAuthRequestDto;
import com.impoort.impoortapi.service.AuthenticationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Api(description = "User login and signup paths")
@RestController
@RequestMapping("/auth")
public class UserAuthController {
    private final AuthenticationService service;

    @Autowired
    public UserAuthController(AuthenticationService service) {
        this.service = service;
    }

    @ApiOperation(value = "Giriş")
    @CrossOrigin
    @PostMapping(path = "/login")
    public ResponseEntity<Object> login(HttpServletRequest httpServletRequest, @RequestBody @Valid final UserAuthRequestDto userAuthRequestDto){
        return new ResponseEntity<Object>(service.login(userAuthRequestDto),HttpStatus.OK);
    }

    @ApiOperation(value = "kayıt")
    @CrossOrigin
    @PostMapping("/signUp")
    public ResponseEntity<UserResponseDTO> addNewUser(@RequestBody @Validated UserRequestDTO userRequestDTO){
        return new ResponseEntity<UserResponseDTO>(service.signUp(userRequestDTO),HttpStatus.OK);
    }
}
