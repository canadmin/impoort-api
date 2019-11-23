package com.impoort.impoortapi.repository.watch;

import com.impoort.impoortapi.domain.watch.Watcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatcherRepository extends PagingAndSortingRepository<Watcher, Integer> {
    void deleteByWatchMapId(UUID watchMapId);

    Page<Watcher> findAllByWatchingUser(String watchedUser, Pageable pageable);

}
