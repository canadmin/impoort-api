package com.impoort.impoortapi.api.v1.model.requestmodel.comment;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
public class CommentRequestDTO {

    @NotNull
    private String commentText;

    @NotNull
    private String user;

}
