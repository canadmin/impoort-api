package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.WatcherPageList;
import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.WatchingPageList;
import com.impoort.impoortapi.domain.watch.Watch;
import org.springframework.data.domain.PageRequest;

import java.util.List;

public interface WatchService {
    Watch watchUser(Watch watch);

    void stopWatching(int watchingId);

    WatcherPageList getWatcher(String userId, String myId, PageRequest pageRequest);

    WatchingPageList getWatching(String userId, String myId, PageRequest pageRequest);
}
