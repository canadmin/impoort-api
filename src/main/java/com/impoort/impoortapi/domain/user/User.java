package com.impoort.impoortapi.domain.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;

import javax.persistence.*;
import java.util.List;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "user_entity")
public class User  {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String userId;

    @Column(unique = true)
    private String activeGuide;
    private String description;
    private boolean isActive;
    private boolean isConfirmed;
    private String department;
    //sadece iki tane kullanıcı türü vardır bu türlerden 1->User 2->
    private int userType;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    @OneToMany( cascade = {CascadeType.ALL})
    @JsonIgnore
    @JoinColumn(name = "watcher_user_id")
    private List<Watcher> watcher;
    @OneToMany( cascade = {CascadeType.ALL})
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
}
