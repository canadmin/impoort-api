package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;

import java.util.List;

public interface DiscoverService {
    List<PostResponseDTO> discoverPosts();
}
