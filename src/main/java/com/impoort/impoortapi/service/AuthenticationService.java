package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.security.UserAuthDto;

public interface AuthenticationService {
    Object login(UserAuthDto userAuthDto);
    UserResponseDTO signUp(UserRequestDTO userRequestDTO);


}
