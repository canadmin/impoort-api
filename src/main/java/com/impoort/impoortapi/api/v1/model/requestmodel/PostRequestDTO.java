package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.List;

@Data
public class PostRequestDTO {
    private String userId;
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String department;

    @Null
    @JsonFormat(pattern="dd-MM-yyyy hh:mm", shape=JsonFormat.Shape.STRING)
    private Timestamp createdDateTime;

}
