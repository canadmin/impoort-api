package com.impoort.impoortapi.domain.pageLists;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public class PostPageList extends PageImpl<PostResponseDTO> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public PostPageList(@JsonProperty("posts") List<PostResponseDTO> posts,
                        @JsonProperty("number") int number,
                        @JsonProperty("size") int size,
                        @JsonProperty("totalElements") Long totalElements){
        super(posts, PageRequest.of(number,size),totalElements);

    }
    public PostPageList(List<PostResponseDTO> posts, Pageable pageable, long total) {
        super(posts, pageable, total);
    }

    public PostPageList(List<PostResponseDTO> posts) {
        super(posts);
    }
}
