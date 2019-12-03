package com.impoort.impoortapi.api.v1.model.responsemodel;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

@Data
public class LikeResponseDTO {

    private int likeId;
    private String userId;
    private PostResponseDTO post;

}

