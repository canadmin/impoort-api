package com.impoort.impoortapi.domain.comment;

import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.postgresql.jdbc.PreferQueryMode;

import javax.persistence.*;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WatchPost {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int watchedPostId;

    private int post;

    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name= "userId")
    private User user;
}
