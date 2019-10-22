package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.LikeResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.PostRepository;
import com.impoort.impoortapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
@RequestMapping("/api/v1/post")
public class PostController {
    private final ModelMapper modelMapper;
    private  final PostService postService;


    public PostController(ModelMapper modelMapper, PostService postService) {
        this.modelMapper = modelMapper;
        this.postService = postService;
    }

    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPost(){
        return new ResponseEntity<List<PostResponseDTO>>(postService.getAllPost(), HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping
    public ResponseEntity<PostResponseDTO> addNewPost(@RequestBody PostRequestDTO postRequestDTO){
        return new ResponseEntity<PostResponseDTO>(postService.addNewPost(postRequestDTO),HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("/{postId}/addComment")
    public ResponseEntity<CommentResponseDTO> addNewComment(@RequestBody CommentRequestDTO commentRequestDTO, @PathVariable int postId){
        return new ResponseEntity<CommentResponseDTO>(postService.addNewComment(postId,commentRequestDTO),HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("{postId}/getAllComment")
    public ResponseEntity<List<CommentResponseDTO>> getPostComment(@PathVariable int postId){
        return  new ResponseEntity<List<CommentResponseDTO>>(postService.getAllComment(postId),HttpStatus.OK);
    }
    @CrossOrigin
    @PostMapping("{postId}/addNewLike")
    public ResponseEntity<LikeResponseDTO> addNewLike(@RequestBody LikeRequestDTO likeRequestDTO,@PathVariable int postId){
        return  new ResponseEntity<LikeResponseDTO>(postService.addNewLike(postId,likeRequestDTO),HttpStatus.OK);
    }
    @CrossOrigin
    @GetMapping("{postId}/getAllLike")
    public ResponseEntity<List<LikeResponseDTO>> getAllLike(@PathVariable int postId){
        return new ResponseEntity<List<LikeResponseDTO>>(postService.getAllLike(postId),HttpStatus.OK);
    }



}
