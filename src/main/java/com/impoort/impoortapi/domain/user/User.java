package com.impoort.impoortapi.domain.user;

import java.util.Date;
import java.util.List;

public class User {

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
    private List<String> experienceCompanies;
    private int employeeCount;
}
