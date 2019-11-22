package com.impoort.impoortapi.repository.postrepositories;

import com.impoort.impoortapi.domain.post.Post;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface PostPagingRepository extends PagingAndSortingRepository<Post,Integer> {
}
