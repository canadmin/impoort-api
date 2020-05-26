package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.comment.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.comment.IDCommentRequestDTO;
import com.impoort.impoortapi.domain.pageLists.PostPageList;
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


    PostPageList listPost(String userId,PageRequest pageRequest,Boolean profilePost);

	void deleteLike(int postId, LikeRequestDTO deleteRequestDto);

	CommentResponseDTO deleteComment(int postId, IDCommentRequestDTO commentRequestDTO);

    PostResponseDTO watchPost(int postId, String userId);

    void deleteWatch(int postId, String userId);

    List<PostResponseDTO> listWatchedPosts(String userId);
}
