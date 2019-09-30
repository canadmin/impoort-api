package com.impoort.impoortapi.repository;

import com.impoort.impoortapi.domain.comment.Comment;
import com.impoort.impoortapi.domain.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post,Integer> {
}
