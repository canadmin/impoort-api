package com.impoort.impoortapi.domain.comment;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.impoort.impoortapi.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @JsonIgnore
    @OneToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name= "userId")
    private User user;
}
