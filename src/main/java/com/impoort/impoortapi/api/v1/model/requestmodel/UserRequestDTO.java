package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.user.User;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

@Data
@Builder
public class UserRequestDTO {

    private String description;
    private String department;//title
    private UserType userType; // startup -

    @NotNull
    private String firstName;
    private String lastName;
    @NotNull
    @NotBlank
    @Email
    private String email;
    private String city;
    @NotNull
    private String birthDate;
    private String gender;
    @NotNull
    private String password;
    private String phoneNumber;
    private List<Experience> experiences; // eğer s ise projeler eklenecek buraya
    private int employeeCount; // s icin
    //eğer startup ise
    private List<User> employees; // s icin
    private Map<String,String> links;
}
