package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.PostRepository;
import org.modelmapper.ModelMapper;
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
        private final PostRepository postRepository;
        private final ModelMapper modelMapper;

    public PostController(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @GetMapping
    public ResponseEntity<List<PostRequestDTO>> getAllPost(){
        List<Post> posts=postRepository.findAll();
        List<PostRequestDTO>  postRequestDTOS= Arrays.asList(modelMapper.map(posts, PostRequestDTO[].class));

        return ResponseEntity.ok(postRequestDTOS);
    }
    @PostMapping
    public ResponseEntity<PostRequestDTO> addNewPost(@RequestBody PostRequestDTO postRequestDTO){

        Post post=modelMapper.map(postRequestDTO,Post.class);
        post.setCommentList(new ArrayList<>());
        return ResponseEntity.ok(modelMapper.map(postRepository.save(post),PostRequestDTO.class));
    }
    @PostMapping("/{postId}/addComment")
    public ResponseEntity<CommentResponseDTO> addNewPost(@RequestBody CommentRequestDTO commentRequestDTO, @PathVariable int postId){
        Post post = postRepository.getOne(postId);
        List<Comment> commentsTemp=post.getCommentList();
        Comment comment=modelMapper.map(commentRequestDTO,Comment.class);
        commentsTemp.add(comment);
        post.setCommentList(commentsTemp);
        postRepository.save(post);
        CommentResponseDTO commentRequestDTO1=modelMapper.map(comment,CommentResponseDTO.class);
        return ResponseEntity.ok(commentRequestDTO1);
    }

}
