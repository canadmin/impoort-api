package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.security.JwtUtil;
import com.impoort.impoortapi.security.UserAuthDto;
import com.impoort.impoortapi.service.AuthenticationService;
import com.impoort.impoortapi.utils.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public Object login(UserAuthDto userAuthDto) {
        User user = userRepository.findByEmail(userAuthDto.getEmail());
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        UserAuthDto userAuthFound = modelMapper.map(user, UserAuthDto.class);
        if (isValidPassword(userAuthDto.getPassword(), userAuthFound.getPassword())) {
            Map<String, Object> jwt = JwtUtil.generateToken(userAuthDto.getEmail());
            HashMap<String, Object> response = new HashMap<String, Object>() {
                {
                    put("token", jwt);
                    put("user", userResponseDTO);
                }
            };
            return response;
        } else {
            return new ResponseEntity(HttpStatus.UNAUTHORIZED);
        }
    }


    private boolean isValidPassword(String reqPassword, String foundPassword) {
        return reqPassword.equals(foundPassword);
    }

    @Override
    public UserResponseDTO signUp(UserRequestDTO userRequestDTO) {
        User user = modelMapper.map(userRequestDTO, User.class);
        user.setActiveGuide(RandomStringGenerator.generateString());
        user = userRepository.save(user);
        UserResponseDTO userResponseDTO = modelMapper.map(user, UserResponseDTO.class);
        return userResponseDTO;
    }
}
