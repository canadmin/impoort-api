package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class LikeRequestDTO {

    @NotNull
    private String user;
}
