package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.PostPageList;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.LikeResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.comment.Like;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.like.LikeRepository;
import com.impoort.impoortapi.repository.postrepositories.PostPagingRepository;
import com.impoort.impoortapi.repository.postrepositories.PostRepository;
import com.impoort.impoortapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.awt.event.WindowAdapter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    
    private final ModelMapper modelMapper;
    private final PostPagingRepository postPagingRepository;
    private final UserRepository userRepository;


    /**
     * Test için yazıldı silinecek
     **/
    @Override
    public List<PostResponseDTO> getAllPost() {
        List<Post> postList = postRepository.findAll();
        List<PostResponseDTO> postRequestDTOS = Arrays.asList(modelMapper.map(postList, PostResponseDTO[].class));
        return postRequestDTOS;
    }

    @Override
    public PostResponseDTO addNewPost(PostRequestDTO postRequestDTO) {
        Post post = modelMapper.map(postRequestDTO, Post.class);
        post.setUser(userRepository.getOne(postRequestDTO.getUserId()));
        post.setCommentList(new ArrayList<>());
        post.setLikeList(new ArrayList<>());
        return modelMapper.map(postRepository.save(post), PostResponseDTO.class);
    }

    /**
     * @param postId            yorum eklenecek post id'si
     * @param commentRequestDTO
     * @return
     */
    @Override
    public CommentResponseDTO addNewComment(int postId, CommentRequestDTO commentRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Comment> commentsTemp = post.getCommentList();
        Comment comment = modelMapper.map(commentRequestDTO, Comment.class);
        comment.setUser(userRepository.getOne(commentRequestDTO.getUser()));
        commentsTemp.add(comment);
        post.setCommentList(commentsTemp);
        post.setCommentCount(commentsTemp.size());
        postRepository.save(post);
        CommentResponseDTO commentResponseDTO = modelMapper.map(comment, CommentResponseDTO.class);
        commentResponseDTO.setCommentId(commentsTemp.get(commentsTemp.size() - 1).getCommentId());
        commentResponseDTO.setPostId(post.getPostId());
        return commentResponseDTO;
    }

    /**
     * @param postId yorumları  görüntülenecek postun ıdsi
     */
    @Override
    public List<CommentResponseDTO> getAllComment(int postId) {
        Post post = postRepository.getOne(postId);
        List<Comment> comments = post.getCommentList();
        List<CommentResponseDTO> commentResponseDTOS = Arrays.asList(modelMapper.map(comments, CommentResponseDTO[].class));
        return commentResponseDTOS;
    }

    /**
     * @param postId beğenileri görüntülenecek postun ıdsi
     */
    @Override
    public List<LikeResponseDTO> getAllLike(int postId) {
        Post post = postRepository.getOne(postId);
        List<Like> likes = post.getLikeList();
        List<LikeResponseDTO> likeResponseDTOS = Arrays.asList(modelMapper.map(likes, LikeResponseDTO[].class));
        return likeResponseDTOS;
    }


    /**
     * @param postId         like atılacak post ıd'si
     * @param likeRequestDTO kullanıcı ıd'sini içeriyor
     * @return like id ve beğenen kullanıcının bilgisi geri dönüyor
     */
    @Override
    public LikeResponseDTO addNewLike(int postId, LikeRequestDTO likeRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Like> likes = post.getLikeList();
        System.out.println(likes.toString());
        Like like = modelMapper.map(likeRequestDTO, Like.class);
        User user = userRepository.getOne(likeRequestDTO.getUser());
        like.setUser(user);
        likes.add(like);
        post.setLikeList(likes);
        post.setLikeCount(likes.size());
        LikeResponseDTO likeResponseDTO = modelMapper.map(like, LikeResponseDTO.class);
        postRepository.save(post);
        likeResponseDTO.setLikeId(likes.get(likes.size() - 1).getLikeId());
        return likeResponseDTO;
    }


    /**
     * @param userId      gelen postları listenelecek kullanıcının id'si
     * @param pageRequest nasıl sayfalanacak mesele 0-15 ilk sayfada gösterilecek eleman sayısı 1,15 ikinci sayfa
     * @param profilePost eğer diğer bir kullanıcının veya kendi profilinindeki kullanıcıları görüntülüyorsan(true || false ) ona göre sorgu atılacak
     * @return dönen obje postPageList türünde postpage list içerik ile beraber sayfa numarası ve o sayfadaki içerik sayısı
     */
    @Override
    public PostPageList listPost(String userId, PageRequest pageRequest, Boolean profilePost) {

        PostPageList postPageList;
        Pageable sortedByCreatedDateTime = PageRequest.of(pageRequest.getPageNumber(), pageRequest.getPageSize(), Sort.by("createdDateTime"));
        Page<Post> postPage = null;


        if (profilePost) {
            postPage = postPagingRepository.findAllByUserId(userId, sortedByCreatedDateTime);

        } else {
            List<String> users = new ArrayList<>();

            for (int i = 0; i < userRepository.getOne(userId).getWatching().size(); i++) {
                users.add(userRepository.getOne(userId).getWatching().get(i).getUser().getUserId());
            }

            postPage = postPagingRepository.findByUserIdIn(users, sortedByCreatedDateTime);
        }

        postPageList = new PostPageList(postPage
                .getContent().stream()
                .map(x -> modelMapper.map(x, PostResponseDTO.class))
                .collect(Collectors.toList()),
                PageRequest.of(postPage.getPageable().getPageNumber(),
                        postPage.getPageable().getPageSize()), postPage.getTotalElements());

        return postPageList;
    }

	@Override
	public LikeResponseDTO deleteLike(int postId, LikeRequestDTO deleteRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Like> likes = post.getLikeList();
        
        Like delete = null;
        for(Like like : likes) {
        	if(like.getUser().getUserId().equals(deleteRequestDTO.getUser())) {
        		delete = like;
        		break;
        	}
        }
          
        likes.remove(delete);        	
        likeRepository.delete(delete);
        post.setLikeCount(likes.size());
        postRepository.save(post);
        
        LikeResponseDTO deleteResponseDTO = modelMapper.map(delete,LikeResponseDTO.class);
        return deleteResponseDTO;
	}

}
