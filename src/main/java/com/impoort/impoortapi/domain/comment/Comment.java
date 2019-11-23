package com.impoort.impoortapi.domain.comment;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int commentId;
    private String commentText;

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp commentDate;

    @OneToOne
    @JoinColumn(name = "commentUserID")
    private User user;
}
