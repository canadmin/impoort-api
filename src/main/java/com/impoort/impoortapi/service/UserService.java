package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserUpdateDto;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;

import java.util.List;

public interface UserService {
    List<UserResponseDTO> getAllUser();
    UserResponseDTO findByActiveGuide(String activeGuide);
    UserResponseDTO updateUser(UserResponseDTO userResponseDTO);
    UserResponseDTO getUser(String userId);
    UserResponseDTO updateUser(UserUpdateDto userUpdateDto);

}
