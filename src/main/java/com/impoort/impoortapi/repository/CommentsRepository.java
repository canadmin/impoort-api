package com.impoort.impoortapi.repository;

import com.impoort.impoortapi.domain.comment.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentsRepository extends JpaRepository<Comment,Integer> {
    public List<Comment> findByPostId(int commentId);
}
