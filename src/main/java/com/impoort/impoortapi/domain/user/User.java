package com.impoort.impoortapi.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.comment.WatchPost;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import java.util.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_entity")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String userId;

    @Column(unique = true)
    private String activeGuide;

    private String description;
    private boolean isActive;
    private boolean isConfirmed;
    private String department;

    //sadece iki tane kullanıcı türü vardır bu türlerden 1->User 2->
    @Enumerated(value = EnumType.ORDINAL)
    private UserType userType;

    private String firstName;
    private String lastName;

    private String fullName;
    @Column(unique = true)
    private String email;


    @OneToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST
    }   )
    @JsonIgnore
    @JoinColumn(name = "watcher_user_id")
    private List<Watcher> watcher;

    @OneToMany(cascade = {
            CascadeType.REFRESH,
            CascadeType.MERGE,
            CascadeType.PERSIST
    })
    @JsonIgnore
    @JoinColumn(name = "watching_user_id")
    private List<Watching> watching;

    private String password;
    private String city;
    private String birthDate;
    private String gender;

    private String phoneNumber;

    private int watcherCount;
    private int watchingCount;
    private int watchingPostCount;
    private int employeeCount;
    private String profileImgUrl;

    @ElementCollection
    @MapKeyColumn(name = "link")
    @Column(name = "linkName")
    @CollectionTable(name = "user_links",joinColumns = @JoinColumn(name = "user_id"))
    private Map<String,String> links = new HashMap<>();

    @OneToMany(mappedBy = "user")
    private List<WatchPost> watchPosts = new ArrayList<>();
}
