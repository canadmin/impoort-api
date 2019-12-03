package com.impoort.impoortapi.api.v1.model.responsemodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
public class PostResponseDTO {

    private int postId;
    private UserResponseDTO user;
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String department;
    private int likeCount;
    private List<UserResponseDTO> likeList;
    private int commentCount;
    private List<Comment> commentList;

    @JsonFormat(pattern="dd-MM-yyyy hh:mm", shape=JsonFormat.Shape.STRING)
    private Timestamp createdDateTime;

    private Collection<String> tags;

    private Boolean isLiked = false;
    private Boolean isWatched = false;
}
