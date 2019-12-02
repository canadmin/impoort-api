package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.SearchRequest;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;

import java.util.List;

public interface SearchService {
    List<UserResponseDTO> getSearchUser(SearchRequest searchRequest);
}
