package com.impoort.impoortapi.api.v1.model.responsemodel;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import java.util.List;

@Data
public class postResponseDTO {

    private int postId;
    private User user;
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String sector;
    private int likeCount;
    private List<User> likeList;
    private int commentCount;
    private List<Comment> commentList;
    private String investmentAmount;
    private String developerCount;
    private String wantedSalary;
}
