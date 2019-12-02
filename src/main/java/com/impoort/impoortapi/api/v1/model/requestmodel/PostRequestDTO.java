package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.impoort.impoortapi.domain.user.User;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

@Data
public class PostRequestDTO {

    @NotNull
    private String userId;

    @NotNull
    private int postType;
    private String mediaUrl;
    private String postDescription;
    private String department; // ?

    @Null
    @JsonFormat(pattern="dd-MM-yyyy hh:mm", shape=JsonFormat.Shape.STRING)
    private Timestamp createdDateTime;

    private Collection<String> tags;

}
