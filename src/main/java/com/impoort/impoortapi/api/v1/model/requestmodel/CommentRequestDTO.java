package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
public class CommentRequestDTO {

    private String commentText;
    private String commentDate;
    private User user;
}
