package com.impoort.impoortapi.repository.comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.impoort.impoortapi.domain.comment.Like;

@Repository
public interface LikeRepository extends JpaRepository<Like,Integer>{

}
