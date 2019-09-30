package com.impoort.impoortapi.domain.comment;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "likes")
public class Like  {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int likeId;
    private int postId;
    @OneToOne
    @JoinColumn(name = "likeUserID")
    private User user;
}
