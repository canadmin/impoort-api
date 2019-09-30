package com.impoort.impoortapi.domain.comment;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    private int postId;
    private String commentText;
    private String commentDate;
    @OneToOne
    @JoinColumn(name = "commentUserID")
    private User user;
}
