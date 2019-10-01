package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class postRequestDTO {
    private User user;
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String sector;
    private String investmentAmount;
    private String developerCount;
    private String wantedSalary;
}
