package com.impoort.impoortapi.repository.watch;

import com.impoort.impoortapi.domain.watch.Watching;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WatchingRepository extends JpaRepository<Watching,Integer> {
}
