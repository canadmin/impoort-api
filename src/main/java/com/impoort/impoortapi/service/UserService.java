package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUser();
    UserResponseDTO saveUser(UserRequestDTO userRequestDTO);
    UserResponseDTO findByActiveGuide(String activeGuide);
    UserResponseDTO updateUser(UserResponseDTO userResponseDTO);


}
