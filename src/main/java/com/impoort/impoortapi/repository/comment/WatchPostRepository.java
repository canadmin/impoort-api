package com.impoort.impoortapi.repository.comment;

import com.impoort.impoortapi.domain.comment.WatchPost;
import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WatchPostRepository extends JpaRepository<WatchPost,Integer > {

    Boolean existsByPostAndUser(int post, User user);
    WatchPost findByPostAndUser(int post,User user);
    List<WatchPost> findAllByUser(User user);
 //   void deleteByPostAndUser(Post post,User user);
}
