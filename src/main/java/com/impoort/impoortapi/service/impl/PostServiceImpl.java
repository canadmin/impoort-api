package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.comment.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.comment.IDCommentRequestDTO;
import com.impoort.impoortapi.domain.pageLists.PostPageList;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.LikeResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.comment.Like;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.comment.WatchPost;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.comment.CommentRepository;
import com.impoort.impoortapi.repository.comment.LikeRepository;
import com.impoort.impoortapi.repository.comment.WatchPostRepository;
import com.impoort.impoortapi.repository.postrepositories.PostPagingRepository;
import com.impoort.impoortapi.repository.postrepositories.PostRepository;
import com.impoort.impoortapi.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final CommentRepository commentRepository;
    
    private final ModelMapper modelMapper;
    private final PostPagingRepository postPagingRepository;
    private final UserRepository userRepository;
    private final WatchPostRepository watchPostRepository;


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
    
	@Override
	public CommentResponseDTO deleteComment(int postId, IDCommentRequestDTO commentRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Comment> comments = post.getCommentList();
        Comment delete = commentRepository.getOne(Integer.parseInt(commentRequestDTO.getCommentId()));   
        
        comments.remove(delete);     
        post.setCommentCount(comments.size());   	
        
        commentRepository.delete(delete);
        postRepository.save(post);
        
        CommentResponseDTO deleteResponseDTO = modelMapper.map(delete,CommentResponseDTO.class);
        deleteResponseDTO.setPostId(postId);
        return deleteResponseDTO;
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
        Like like = modelMapper.map(likeRequestDTO, Like.class);
        User user = userRepository.getOne(likeRequestDTO.getUser());
        like.setUserId(user.getUserId());
        like.setPost(post);
        likes.add(like);
        post.setLikeList(likes);
        post.setLikeCount(likes.size());
        LikeResponseDTO likeResponseDTO = modelMapper.map(like, LikeResponseDTO.class);
        postRepository.save(post);
        likeResponseDTO.setLikeId(likes.get(likes.size() - 1).getLikeId());
        return likeResponseDTO;
    }
    
    @Override
	public LikeResponseDTO deleteLike(int postId, LikeRequestDTO deleteRequestDTO) {
        Post post = postRepository.getOne(postId);
        List<Like> likes = post.getLikeList();
        // TODO bunu çözelim hasancığım satır 218 deki gibi :)
        Like delete = null;
        for(Like like : likes) {
        	if(like.getUserId().equals(deleteRequestDTO.getUser())) {
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


    /**
     * @param userId      gelen postları listenelecek kullanıcının id'si
     * @param pageRequest nasıl sayfalanacak mesele 0-15 ilk sayfada gösterilecek eleman sayısı 1,15 ikinci sayfa
     * @param profilePost eğer diğer bir kullanıcının veya kendi profilinindeki kullanıcıları görüntülüyorsan(true || false ) ona göre sorgu atılacak
     * @return dönen obje postPageList türünde postpage list içerik ile beraber sayfa numarası ve o sayfadaki içerik sayısı
     */
    @Override
    public PostPageList listPost(String userId, PageRequest pageRequest, Boolean profilePost) {

        Optional<User> user1 = userRepository.findById(userId);
        PostPageList postPageList;
        Pageable sortedByCreatedDateTime = PageRequest.of(pageRequest.getPageNumber(),
                pageRequest.getPageSize(), Sort.by("createdDateTime"));
        Page<Post> postPage = null;
        //postlar görüntülenirken benim beğendiğime göre bulunması gerekli

        if (profilePost) {
            postPage = postPagingRepository.findAllByUserId(userId, sortedByCreatedDateTime);

        } else {
            List<String> users = new ArrayList<>();
            users.add(userId);
            for (int i = 0; i < userRepository.getOne(userId).getWatching().size(); i++) {
                users.add(userRepository.getOne(userId).getWatching().get(i).getUser().getUserId());
            }

            postPage = postPagingRepository.findByUserIdIn(users, sortedByCreatedDateTime);
        }

        List<PostResponseDTO> updatedPostPage =
                postPage.getContent()
                        .stream()
                        .map(x-> modelMapper.map(x,PostResponseDTO.class))
                        .collect(Collectors.toList());

        //listenecek postlarda benim beğenip beğenmediğim bilgisi ekleniyor
        // listenecek postlarda benim watchlayıp watchlamadığım görünecek
        AtomicInteger index = new AtomicInteger();
        List<WatchPost> watchPostList = watchPostRepository.findAllByUser(user1.get());

        updatedPostPage.stream().forEach(post->{
            if(post.getLikeList().get(index.getAndIncrement()).getUserId().equals(userId)){
                post.setIsLiked(true);
                post.setLikeList(null);
            }
            if(!watchPostList.isEmpty()){
                if(post.getPostId() == watchPostList.get(index.decrementAndGet()).getPost()){
                    post.setIsWatched(true);
                }
            }

        });


        postPageList = new PostPageList(updatedPostPage,
                PageRequest.of(postPage.getPageable().getPageNumber(),
                        postPage.getPageable().getPageSize()), postPage.getTotalElements());
        return postPageList;
    }

    @Override
    public PostResponseDTO watchPost(int postId, String userId) {
        User user = userRepository.getOne(userId);
        Post post = postRepository.getOne(postId);

        Boolean isExist = watchPostRepository.existsByPostAndUser(postId,user);
        if(!isExist){
            user.setWatchingPostCount(user.getWatchingPostCount()+1);
            WatchPost watchPost = WatchPost.builder().user(user).post(postId).build();
            watchPostRepository.save(watchPost);

            return modelMapper.map(postRepository.getOne(postId),PostResponseDTO.class);
        }
        return null;
    }

    @Override
    public void deleteWatch(int postId, String userId) {
        User user = userRepository.getOne(userId);
        user.setWatchingPostCount(user.getWatchingPostCount()-1);
        WatchPost watchPost =  watchPostRepository.findByPostAndUser(postId,user);
        watchPostRepository.deleteById(watchPost.getWatchedPostId());
    }
}
