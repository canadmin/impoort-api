package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.WatcherPageList;
import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.WatchingPageList;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.watch.Watch;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.postrepositories.PostPagingRepository;
import com.impoort.impoortapi.repository.postrepositories.PostRepository;
import com.impoort.impoortapi.repository.watch.WatcherRepository;
import com.impoort.impoortapi.repository.watch.WatchingRepository;
import com.impoort.impoortapi.service.WatchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.support.PagedListHolder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class WatchServiceImpl implements WatchService {

    private final UserRepository userRepository;
    private final WatchingRepository watchingRepository;
    private final WatcherRepository watcherRepository;
    private final PostRepository postRepository;
    private final ModelMapper modelMapper;
    private final PostPagingRepository postPagingRepository;


    @Override
    public Watch watchUser(Watch watch) {

        UUID mapUUID = UUID.randomUUID();

        User me = userRepository.getOne(watch.getWatcherId());
        User other = userRepository.getOne(watch.getWatchingId());

        List<Watching> myWatchingList = me.getWatching();

        Watching newWatching = new Watching();
        newWatching.setUser(other);
        newWatching.setWatchMapId(mapUUID);
        newWatching.setWatcherUser(other.getUserId());
        myWatchingList.add(newWatching);

        me.setWatching(myWatchingList);

        userRepository.save(me);

        List<Watcher> otherWatcherList = other.getWatcher();

        Watcher newWatcher = new Watcher();
        newWatcher.setUser(me);
        newWatcher.setWatchMapId(mapUUID);
        newWatcher.setWatchingUser(me.getUserId());
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
        Optional<Watching> watching = watchingRepository.findById(watchingId);

        watcherRepository.deleteByWatchMapId(watching.get().getWatchMapId());

        watchingRepository.deleteById(watchingId);

    }

    @Override
    public WatcherPageList getWatcher(String userId, String myId, PageRequest pageRequest) {

        WatcherPageList watcherPageList;

        List<Watcher> watchers = userRepository.getOne(userId).getWatcher();
        List<Watching> myWatchingList=userRepository.getOne(myId).getWatching();

        for(int i =0 ; i < myWatchingList.size(); i++){
          for (int j = 0; j < watchers.size() ; j++) {
                if(watchers.get(j).getUser().getUserId() == myWatchingList.get(i).getUser().getUserId()){
                    watchers.get(j).setBeingWatch(true);
                }
            }
        }

        PagedListHolder page = new PagedListHolder(watchers);

        page.setPageSize(pageRequest.getPageSize());
        page.setPage(pageRequest.getPageNumber());


        watcherPageList = new WatcherPageList(page.getSource(),
                PageRequest.of(pageRequest.getPageNumber(),pageRequest.getPageSize()),
                page.getSource().size());
        return watcherPageList;
    }

    @Override
    public WatchingPageList getWatching(String userId, String myId, PageRequest pageRequest) {

        WatchingPageList watchingPageList;

        List<Watching> watchings = userRepository.getOne(userId).getWatching();
        List<Watching> myWatchingList=userRepository.getOne(myId).getWatching();

        for(int i =0 ; i < myWatchingList.size(); i++){
            for (int j = 0; j < watchings.size() ; j++) {
                if(watchings.get(j).getUser().getUserId() == myWatchingList.get(i).getUser().getUserId()){
                    watchings.get(j).setBeingWatch(true);
                }
            }
        }

        PagedListHolder page = new PagedListHolder(watchings);

        page.setPageSize(pageRequest.getPageSize());
        page.setPage(pageRequest.getPageNumber());


        watchingPageList = new WatchingPageList(page.getSource(),
                PageRequest.of(pageRequest.getPageNumber(),pageRequest.getPageSize()),
                page.getSource().size());
        return watchingPageList;
    }

}
