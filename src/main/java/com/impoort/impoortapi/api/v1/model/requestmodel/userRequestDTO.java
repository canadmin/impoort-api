package com.impoort.impoortapi.api.v1.model.requestmodel;

import lombok.Data;

import java.util.Date;

@Data
public class userRequestDTO {

    private String description;
    private String sector;
    private int userType;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private Date birthDate;
    private String gender;
    private String phoneNumber;
    private String experienceYear;
    private String experienceCompanies;
    private int employeeCount;
}
