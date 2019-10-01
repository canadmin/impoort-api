package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.repository.PostRepository;
import com.impoort.impoortapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Service
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, ModelMapper modelMapper) {
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<PostResponseDTO> getAllPost() {
        List<Post> postList=postRepository.findAll();
        List<PostResponseDTO>  postRequestDTOS= Arrays.asList(modelMapper.map(postList, PostResponseDTO[].class));
        return postRequestDTOS;
    }

    @Override
    public PostResponseDTO addNewPost(PostRequestDTO postRequestDTO) {
        Post post=modelMapper.map(postRequestDTO,Post.class);
        post.setCommentList(new ArrayList<>());
        return  modelMapper.map(postRepository.save(post),PostResponseDTO.class);
    }

    @Override
    public CommentResponseDTO addNewComment(int postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Comment> commentsTemp=post.getCommentList();
        Comment comment=modelMapper.map(commentRequestDTO,Comment.class);
        commentsTemp.add(comment);
        post.setCommentList(commentsTemp);
        postRepository.save(post);
        CommentResponseDTO commentResponseDTO=modelMapper.map(comment,CommentResponseDTO.class);
        return commentResponseDTO;
    }

    @Override
    public List<CommentResponseDTO> getAllComment(int postId) {
        Post post =postRepository.getOne(postId);
        List<Comment> comments=post.getCommentList();
        List<CommentResponseDTO> commentResponseDTOS=Arrays.asList(modelMapper.map(comments,CommentResponseDTO[].class));
        return commentResponseDTOS;
    }
}
