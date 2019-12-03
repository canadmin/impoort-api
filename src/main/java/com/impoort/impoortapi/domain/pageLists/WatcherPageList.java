package com.impoort.impoortapi.domain.pageLists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.impoort.impoortapi.domain.watch.Watcher;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class WatcherPageList extends PageImpl<Watcher> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WatcherPageList(@JsonProperty("watcher") List<Watcher> watcher,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements){
        super(watcher, PageRequest.of(number,size),totalElements);

    }
    public WatcherPageList(List<Watcher> watchers, Pageable pageable, long total) {
        super(watchers, pageable, total);
    }

    public WatcherPageList(List<Watcher> watchers) {
        super(watchers);
    }
}
