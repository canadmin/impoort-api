package com.impoort.impoortapi.domain.post;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.comment.Like;
import com.impoort.impoortapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int postId;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user")
    private User user;

    private String userId;

    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String department;
    private int likeCount;
    private int commentCount;

    @OneToMany(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH},mappedBy = "post")
    private List<Like> likeList;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "comment_post_id")
    private List<Comment> commentList;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdDateTime;

    @ElementCollection
    @CollectionTable(name = "post_tag",joinColumns = @JoinColumn(name = "post_id"))
    @Column(name = "tag")
    private Collection<String> tags = new ArrayList<>();
}