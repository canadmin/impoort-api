package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.PostPageList;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.LikeResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.comment.Like;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.repository.postrepositories.PostPagingRepository;
import com.impoort.impoortapi.repository.postrepositories.PostRepository;
import com.impoort.impoortapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final PostPagingRepository postPagingRepository;




    @Override
    public List<PostResponseDTO> getAllPost() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDTO> postRequestDTOS = Arrays.asList(modelMapper.map(postList, PostResponseDTO[].class));
        return postRequestDTOS;
    }

    @Override
    public PostResponseDTO addNewPost(PostRequestDTO postRequestDTO) {
        Post post = modelMapper.map(postRequestDTO, Post.class);
        post.setCommentList(new ArrayList<>());
        post.setLikeList(new ArrayList<>());
        return modelMapper.map(postRepository.save(post), PostResponseDTO.class);
    }

    @Override
    public CommentResponseDTO addNewComment(int postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Comment> commentsTemp = post.getCommentList();
        Comment comment = modelMapper.map(commentRequestDTO, Comment.class);
        commentsTemp.add(comment);
        post.setCommentList(commentsTemp);
        post.setCommentCount(commentsTemp.size());
        postRepository.save(post);
        CommentResponseDTO commentResponseDTO = modelMapper.map(comment, CommentResponseDTO.class);
        commentResponseDTO.setCommentId(commentsTemp.get(commentsTemp.size() - 1).getCommentId());
        return commentResponseDTO;
    }

    @Override
    public List<CommentResponseDTO> getAllComment(int postId) {
        Post post = postRepository.getOne(postId);
        List<Comment> comments = post.getCommentList();
        List<CommentResponseDTO> commentResponseDTOS = Arrays.asList(modelMapper.map(comments, CommentResponseDTO[].class));
        return commentResponseDTOS;
    }

    @Override
    public List<LikeResponseDTO> getAllLike(int postId) {
        Post post = postRepository.getOne(postId);
        List<Like> likes = post.getLikeList();
        List<LikeResponseDTO> likeResponseDTOS = Arrays.asList(modelMapper.map(likes, LikeResponseDTO[].class));
        return likeResponseDTOS;
    }

    @Override
    public LikeResponseDTO addNewLike(int postId, LikeRequestDTO likeRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Like> likes = post.getLikeList();
        Like like = modelMapper.map(likeRequestDTO, Like.class);
        likes.add(like);
        post.setLikeList(likes);
        post.setLikeCount(likes.size());
        LikeResponseDTO likeResponseDTO = modelMapper.map(like, LikeResponseDTO.class);
        postRepository.save(post);
        likeResponseDTO.setLikeId(likes.get(likes.size() - 1).getLikeId());
        return likeResponseDTO;
    }


    /**
     *todo şuan tüm postlarda arama yapılıyor
     *Senaryo : Post-userId benim takipçi listeme göre filtrelenecek
     **/
    @Override
    public PostPageList listPost(PageRequest pageRequest) {

        PostPageList postPageList;

        Page<Post> postPage = postPagingRepository.findAll(pageRequest);

        postPageList = new PostPageList(postPage
                .getContent().stream()
                .map(x -> modelMapper.map(x,PostResponseDTO.class))
                .collect(Collectors.toList())
                ,
                PageRequest.of(postPage.getPageable().getPageNumber(),
                postPage.getPageable().getPageSize()),postPage.getTotalElements());

        return postPageList;
    }

}
