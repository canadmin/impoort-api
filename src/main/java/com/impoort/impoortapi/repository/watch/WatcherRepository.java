package com.impoort.impoortapi.repository.watch;

import com.impoort.impoortapi.domain.watch.Watcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface WatcherRepository extends JpaRepository<Watcher, Integer> {
    void deleteByWatchMapId(UUID watchMapId);
}
