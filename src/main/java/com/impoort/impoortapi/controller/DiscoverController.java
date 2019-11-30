package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.service.DiscoverService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/api/v1/discover/")
public class DiscoverController {

    private final DiscoverService discoverService;

    public DiscoverController(DiscoverService discoverService) {
        this.discoverService = discoverService;
    }

    @CrossOrigin
    @GetMapping("post")
    public ResponseEntity<List<PostResponseDTO>> discoverPosts(){
        return new ResponseEntity<>(discoverService.discoverPosts(), HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("suggested")
    public ResponseEntity<List<UserResponseDTO>> discoverUser(){
        return new ResponseEntity<>(discoverService.discoverUser(), HttpStatus.OK);
    }
}
