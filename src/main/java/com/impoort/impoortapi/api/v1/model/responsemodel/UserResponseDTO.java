package com.impoort.impoortapi.api.v1.model.responsemodel;

import lombok.Data;

import java.util.Date;

@Data
public class UserResponseDTO {

    private String userId;
    private String activeGuide;
    private String description;
    private boolean isActive;
    private boolean isConfirmed;
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
