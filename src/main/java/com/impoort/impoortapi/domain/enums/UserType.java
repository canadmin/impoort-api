package com.impoort.impoortapi.domain.enums;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.NUMBER)
public enum  UserType {
    INVESTOR,STARTUP,DEVELOPER,NORMAL_USER;


}
