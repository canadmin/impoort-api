package com.impoort.impoortapi.api.v1.model.responsemodel;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
public class UserMessageDTO {

    private String userId;
    private String firstName;
    private String lastName;
    private String profileImgUrl;
    private String lastMessage;


}
