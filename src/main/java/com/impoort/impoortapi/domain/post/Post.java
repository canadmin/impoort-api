package com.impoort.impoortapi.domain.post;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.user.User;

import java.util.List;

public class Post {
    private String postId;
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

    //Hasan
}