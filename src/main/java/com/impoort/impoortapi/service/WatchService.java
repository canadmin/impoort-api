package com.impoort.impoortapi.service;

import com.impoort.impoortapi.domain.pageLists.WatcherPageList;
import com.impoort.impoortapi.domain.pageLists.WatchingPageList;
import com.impoort.impoortapi.domain.watch.Watch;
import org.springframework.data.domain.PageRequest;

public interface WatchService {
    Watch watchUser(String watcherId ,String watchingId);

    void stopWatching(int watchingId);

    WatcherPageList getWatcher(String userId, String myId, PageRequest pageRequest);

    WatchingPageList getWatching(String userId, String myId, PageRequest pageRequest);
}
