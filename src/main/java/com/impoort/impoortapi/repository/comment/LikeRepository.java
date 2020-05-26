package com.impoort.impoortapi.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impoort.impoortapi.domain.comment.Like;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Integer>{

    List<Like> findAllByUserId(String userId);

}
