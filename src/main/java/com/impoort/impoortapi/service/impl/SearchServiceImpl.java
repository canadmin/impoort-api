package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.SearchRequest;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SearchServiceImpl implements SearchService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;
    @Override
    public List<UserResponseDTO> getSearchUser(SearchRequest searchRequest) {
       List<User> users= userRepository.findAllByFullNameContainingAndUserTypeIn(
               searchRequest.getFullName(),
               searchRequest.getUserTypes()
       );

       List<UserResponseDTO> userResponseDTOS = Arrays.asList(modelMapper.map(users,UserResponseDTO[].class));
       return userResponseDTOS;
    }
}
