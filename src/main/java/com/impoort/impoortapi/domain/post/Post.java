package com.impoort.impoortapi.domain.post;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;
    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String sector;
    private int likeCount;
    @OneToMany
    @JoinColumn
    private List<User> likeList;
    private int commentCount;
    @OneToMany
    @JoinColumn
    private List<Comment> commentList;
    private String investmentAmount;
    private String developerCount;
    private String wantedSalary;
}