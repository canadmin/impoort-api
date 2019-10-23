package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.company.Experience;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Data
public class UserRequestDTO {

    private String description;
    private String department;
    private int userType;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String birthDate;
    private String gender;
    private String password;
    private String phoneNumber;
    private List<Experience> experiences;
    private int employeeCount;
}
