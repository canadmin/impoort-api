package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.postrepositories.PostRepository;
import com.impoort.impoortapi.service.DiscoverService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscoverServiceImpl implements DiscoverService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    @Override
    public List<PostResponseDTO> discoverPosts() {
        List<PostResponseDTO> posts = Arrays.asList(modelMapper.map(postRepository.getPost(PageRequest.of(0,10)), PostResponseDTO[].class));
        return posts;
    }

    @Override
    public List<UserResponseDTO> discoverUser() {
        return Arrays.asList(modelMapper.map(userRepository.getSuggestedUser(PageRequest.of(0,26)),UserResponseDTO[].class));
    }
}
