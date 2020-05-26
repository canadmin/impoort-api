package com.impoort.impoortapi.repository.postrepositories;

import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.comment.Like;
import com.impoort.impoortapi.domain.post.Post;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {

    @Query("select post from Post post where post.postType = 1 order by post.likeCount desc ")
    List<Post> getPost(Pageable pageable);
    List<Post> findAllByPostIdIn(List<Integer> postId);

}
