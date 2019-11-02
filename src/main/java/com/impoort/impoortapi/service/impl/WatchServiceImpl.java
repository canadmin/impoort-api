package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.watch.Watch;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchServiceImpl implements WatchService {
    private final UserRepository userRepository;

    @Autowired
    public WatchServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Watch watchUser(Watch watch) {
        User me = userRepository.getOne(watch.getWatcherId());
        User other = userRepository.getOne(watch.getWatchingId());
        List<Watching> myWatchingList = me.getWatching();// benim  izkedikerimin listesi
        Watching newWatching = new Watching(); // yeni kişiyi izlediklerim listesine ekle
        newWatching.setUser(other);
        myWatchingList.add(newWatching);
        me.setWatching(myWatchingList);
        userRepository.save(me);

        // şimdi izlediğim kişinin watcherlerini güncelleyelim
        List<Watcher> otherWatcherList=other.getWatcher();
        Watcher newWatcher = new Watcher();
        newWatcher.setUser(me);
        otherWatcherList.add(newWatcher);
        other.setWatcher(otherWatcherList);
        userRepository.save(other);
        return watch;
    }
}
