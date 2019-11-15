package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.watch.Watch;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.watch.WatcherRepository;
import com.impoort.impoortapi.repository.watch.WatchingRepository;
import com.impoort.impoortapi.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class WatchServiceImpl implements WatchService {

    private final UserRepository userRepository;
    private final WatchingRepository watchingRepository;
    private final WatcherRepository watcherRepository;

    public WatchServiceImpl(UserRepository userRepository,
                            WatchingRepository watchingRepository,
                            WatcherRepository watcherRepository) {
        this.userRepository = userRepository;
        this.watchingRepository = watchingRepository;
        this.watcherRepository = watcherRepository;
    }

    @Override
    public Watch watchUser(Watch watch) {

        UUID mapUUID = UUID.randomUUID();

        User me = userRepository.getOne(watch.getWatcherId());
        User other = userRepository.getOne(watch.getWatchingId());

        List<Watching> myWatchingList = me.getWatching();

        Watching newWatching = new Watching();
        newWatching.setUser(other);
        newWatching.setWatchMapId(mapUUID);

        myWatchingList.add(newWatching);

        me.setWatching(myWatchingList);

        userRepository.save(me);

        List<Watcher> otherWatcherList = other.getWatcher();

        Watcher newWatcher = new Watcher();
        newWatcher.setUser(me);
        newWatcher.setWatchMapId(mapUUID);

        otherWatcherList.add(newWatcher);

        other.setWatcher(otherWatcherList);

        userRepository.save(other);

        return watch;
    }

    /*
     *TODO takibi bırakmak için ilk önce kullanıcının takip ettikleri listesinden takip ilgili takip silinecek
     *TODO sonra takip etmeyi bıraktığı kullanıcının takipcileri listesinden ilgili watcher silinecek
     */
    @Override
    public void stopWatching(int watchingId) {
        Watching watching = watchingRepository.getOne(watchingId);

        watcherRepository.deleteByWatchMapId(watching.getWatchMapId());

        watchingRepository.deleteById(watchingId);

    }
}
