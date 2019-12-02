package com.impoort.impoortapi.api.v1.model.requestmodel;

import com.impoort.impoortapi.domain.enums.UserType;
import lombok.Data;

import java.util.List;

@Data
public class SearchRequest {

    private String fullName;

    private List<UserType> userTypes;

}
