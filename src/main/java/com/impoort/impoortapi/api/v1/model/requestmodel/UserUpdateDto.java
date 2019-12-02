package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
public class UserUpdateDto {
    @NotNull
    private String userId;
    private String description;
    private String department;//title
    private UserType userType; // startup -
    private String firstName;
    private String lastName;
    private String email;
    private String city;
    private String birthDate;
    private String gender;
    private String password;
    private String phoneNumber;
    private List<Experience> experiences; // eğer s ise projeler eklenecek buraya
    private int employeeCount; // s icin
    //eğer startup ise
    private List<User> employees; // s icin
    private String profileImgUrl;

    private Map<String,String> links;

}
