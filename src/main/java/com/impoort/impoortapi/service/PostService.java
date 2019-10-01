package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.domain.post.Post;

import java.util.List;

public interface PostService {

    List<PostResponseDTO> getAllPost();

    PostResponseDTO addNewPost(PostRequestDTO postRequestDTO);

    CommentResponseDTO addNewComment(int postId, CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> getAllComment(int postId);

}
