package com.impoort.impoortapi.security.authDto;

import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponseDto {


    private HashMap token;
    private UserResponseDTO user;

}
