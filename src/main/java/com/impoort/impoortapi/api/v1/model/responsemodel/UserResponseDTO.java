package com.impoort.impoortapi.api.v1.model.responsemodel;

import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class UserResponseDTO {

    private String userId;
    private String activeGuide;
    private String description;
    private String fullName;
    private boolean isActive;
    private boolean isConfirmed;
    private String department;
    private UserType userType;
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String birthDate;
    private String gender;
    private String phoneNumber;
    private List<Experience> experiences;
    private int employeeCount;
    //eğer startup ise
    private List<UserResponseDTO> employees;
    private String profileImgUrl;

    private int watcherCount;
    private int watchingCount;
    private int watchingPostCount;
    private Map<String,String> links;
}
