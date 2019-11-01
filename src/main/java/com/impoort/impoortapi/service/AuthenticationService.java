package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.security.authDto.UserAuthRequestDto;
import com.impoort.impoortapi.security.authDto.UserAuthResponseDto;

public interface AuthenticationService {

    Object login(UserAuthRequestDto userAuthRequestDto);

    UserResponseDTO signUp(UserRequestDTO userRequestDTO);


}
