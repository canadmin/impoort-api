package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.PostPageList;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.LikeResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface PostService {

    List<PostResponseDTO> getAllPost();

    PostResponseDTO addNewPost(PostRequestDTO postRequestDTO);

    CommentResponseDTO addNewComment(int postId, CommentRequestDTO commentRequestDTO);

    List<CommentResponseDTO> getAllComment(int postId);

    List<LikeResponseDTO> getAllLike(int postId);

    LikeResponseDTO addNewLike(int postId, LikeRequestDTO likeRequestDTO);


    PostPageList listPost( PageRequest pageRequest);
}
