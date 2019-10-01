package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.service.UserService;
import com.impoort.impoortapi.utils.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> userList=userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS= Arrays.asList(modelMapper.map(userList,UserResponseDTO[].class));
        return userResponseDTOS;
    }

    @Override
    public UserResponseDTO saveUser(UserRequestDTO userRequestDTO) {
        User user=modelMapper.map(userRequestDTO,User.class);
        user.setActiveGuide(RandomStringGenerator.generateString());
        user=userRepository.save(user);
        UserResponseDTO userResponseDTO=modelMapper.map(user,UserResponseDTO.class);
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO findByActiveGuide(String activeGuide) {
        UserResponseDTO userResponseDTO = modelMapper.map(userRepository.findByActiveGuide(activeGuide), UserResponseDTO.class);
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO updateUser(UserResponseDTO userResponseDTO) {
        User updatedUser = modelMapper.map(userResponseDTO, User.class);
        return modelMapper.map(userRepository.save(updatedUser), UserResponseDTO.class);
    }
}
