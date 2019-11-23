package com.impoort.impoortapi.api.v1.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import java.sql.Timestamp;


@Data
public class CommentResponseDTO {

    private int commentId;
    private int postId;
    private String commentText;

    @JsonFormat(pattern="dd-MM-yyyy hh:mm", shape=JsonFormat.Shape.STRING)
    private Timestamp commentDate;

    private UserResponseDTO user;

}
