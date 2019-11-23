package com.impoort.impoortapi.repository.watch;

import com.impoort.impoortapi.domain.watch.Watching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchingRepository extends PagingAndSortingRepository<Watching,Integer> {
    Page<Watching> findAllByWatcherUser(String watchedUser, Pageable pageable);

}
