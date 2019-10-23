package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import java.util.List;

@Data
public class PostRequestDTO {
    private User user;
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String department;
    private String investmentAmount;
    private String developerCount;
    private String wantedSalary;
}
