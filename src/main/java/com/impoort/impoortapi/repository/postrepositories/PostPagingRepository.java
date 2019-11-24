package com.impoort.impoortapi.repository.postrepositories;

import com.impoort.impoortapi.domain.post.Post;
import com.impoort.impoortapi.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface PostPagingRepository extends PagingAndSortingRepository<Post,Integer> {

     Page<Post> findByUserIdIn(List<String> userId, Pageable pageable);

     Page<Post> findAllByUserId(String userId,Pageable pageable);

}
