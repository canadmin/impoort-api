package com.impoort.impoortapi.domain.pageLists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.impoort.impoortapi.domain.watch.Watching;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class WatchingPageList extends PageImpl<Watching> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public WatchingPageList(@JsonProperty("watching") List<Watching> watching,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements){
        super(watching, PageRequest.of(number,size),totalElements);

    }
    public WatchingPageList(List<Watching> watchings, Pageable pageable, long total) {
        super(watchings, pageable, total);
    }

    public WatchingPageList(List<Watching> watchings) {
        super(watchings);
    }
}
