package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.SearchRequest;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> searchUser(@RequestBody SearchRequest searchRequest){
        return new ResponseEntity<>(searchService.getSearchUser(searchRequest), HttpStatus.OK);
    }

}
