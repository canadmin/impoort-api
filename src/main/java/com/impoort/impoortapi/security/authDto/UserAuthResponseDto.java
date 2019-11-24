package com.impoort.impoortapi.security.authDto;

import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthResponseDto {

    private String token;
    private UserResponseDTO user;

}
