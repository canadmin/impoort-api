package com.impoort.impoortapi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String userId;

    @Column(unique = true)
    private String activeGuide;
    private String description;
    private boolean isActive;
    private boolean isConfirmed;
    private String sector;
    private int userType;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String email;
    private String password;
    private String city;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    private int watcherCount;
    private int watchingCount;
    private int watchingPostCount;
    private String experienceYear;
    private String experienceCompanies;
    private int employeeCount;
    private String profileImgUrl;
}
