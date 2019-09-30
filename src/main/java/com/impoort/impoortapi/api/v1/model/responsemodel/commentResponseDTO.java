package com.impoort.impoortapi.api.v1.model.responsemodel;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;


@Data
public class commentResponseDTO {

    private int commentId;
    private int postId;
    private String commentText;
    private String commentDate;
    private User user;

}
