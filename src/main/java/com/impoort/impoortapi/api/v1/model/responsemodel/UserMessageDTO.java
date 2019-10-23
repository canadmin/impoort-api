package com.impoort.impoortapi.api.v1.model.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class UserMessageDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String profileImgUrl;
    private String lastMessage;


}
