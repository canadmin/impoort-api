package com.impoort.impoortapi.service;

import com.impoort.impoortapi.domain.watch.Watch;

public interface WatchService {
    Watch watchUser(Watch watch);

    void stopWatching(int watchingId);
}
