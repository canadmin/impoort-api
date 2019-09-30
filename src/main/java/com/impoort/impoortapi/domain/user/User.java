package com.impoort.impoortapi.domain.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "system-uuid")
    @GenericGenerator(name = "system-uuid",strategy = "uuid")
    private String userId;
    private String ActiveGuide;
    private String description;
    private boolean isActive;
    private boolean isConfirmed;
    private String sector;
    private int userType;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String city;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String experienceYear;
    private String experienceCompanies;
    private int employeeCount;
}
