package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.LikeRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.PostRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.comment.CommentRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.comment.IDCommentRequestDTO;
import com.impoort.impoortapi.domain.pageLists.PostPageList;
import com.impoort.impoortapi.api.v1.model.responsemodel.CommentResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.LikeResponseDTO;
import com.impoort.impoortapi.api.v1.model.responsemodel.PostResponseDTO;
import com.impoort.impoortapi.service.PostService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/post")
public class PostController {
    private final ModelMapper modelMapper;
    private  final PostService postService;

    private static final Integer DEFAULT_PAGE_NUMBER = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 25;

    public PostController(ModelMapper modelMapper, PostService postService) {
        this.modelMapper = modelMapper;
        this.postService = postService;
    }

  /*  @CrossOrigin
    @GetMapping
    public ResponseEntity<List<PostResponseDTO>> getAllPost(){
        return new ResponseEntity<List<PostResponseDTO>>(postService.getAllPost(), HttpStatus.OK);
    }*/
    @ApiOperation("yeni post eklemek için")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<PostResponseDTO> addNewPost(@RequestBody @Valid PostRequestDTO postRequestDTO){
        return new ResponseEntity<PostResponseDTO>(postService.addNewPost(postRequestDTO),HttpStatus.OK);
    }

    @ApiOperation(("var olan gönderiye yeni yorum eklemek için"))
    @CrossOrigin
    @PostMapping("/{postId}/addComment")
    public ResponseEntity<CommentResponseDTO> addNewComment(@RequestBody @Valid CommentRequestDTO  commentRequestDTO, @PathVariable int postId){
        return new ResponseEntity<CommentResponseDTO>(postService.addNewComment(postId,commentRequestDTO),HttpStatus.OK);
    }
    @ApiOperation("yapılan yorumu silmek için ")
    @CrossOrigin
    @PostMapping("/{postId}/deleteComment")
    public ResponseEntity<CommentResponseDTO> deleteComment(@RequestBody @Valid IDCommentRequestDTO commentRequestDTO, @PathVariable int postId){
        return new ResponseEntity<CommentResponseDTO>(postService.deleteComment(postId,commentRequestDTO),HttpStatus.OK);
    }

    @ApiOperation(value = "bir postun bütün yorumlarını görüntülemek için kullanılır")
    @CrossOrigin
    @GetMapping("{postId}/getAllComment")
    public ResponseEntity<List<CommentResponseDTO>> getPostComment(@PathVariable int postId){
        return  new ResponseEntity<List<CommentResponseDTO>>(postService.getAllComment(postId),HttpStatus.OK);
    }

    @ApiOperation(value = "var olan postu beğenmek için")
    @CrossOrigin
    @PostMapping("{postId}/addNewLike")
    public ResponseEntity<LikeResponseDTO> addNewLike(@RequestBody @Valid LikeRequestDTO likeRequestDTO,@PathVariable int postId){
        return  new ResponseEntity<LikeResponseDTO>(postService.addNewLike(postId,likeRequestDTO),HttpStatus.OK);
    }
    @ApiOperation(value = "beğeniyi geri almak için")
    @CrossOrigin
    @DeleteMapping("{postId}/deleteLike")
    public ResponseEntity<String> deleteLike(@RequestBody @Valid LikeRequestDTO likeRequestDTO,@PathVariable int postId){
        postService.deleteLike(postId,likeRequestDTO);
        return  new ResponseEntity<String>("Başarı ile silindi",HttpStatus.OK);
    }
    @ApiOperation(value = "Sadece Web için yazıldı beğeni geri almak için")
    @CrossOrigin
    @PostMapping("/deleteLikeWeb")
    public ResponseEntity<String> deleteLikeforWeb(@RequestParam(value = "userId", required = false) String userId,
                                                            @RequestParam(value = "postId", required = false) Integer postId){
        LikeRequestDTO likeRequestDTO = new LikeRequestDTO();
        likeRequestDTO.setUser(userId);
        postService.deleteLike(postId,likeRequestDTO);
        return  new ResponseEntity<>("Başarı ile silindi",HttpStatus.OK);
    }

    @ApiOperation(value = "postun bütün beğenilerini görüntülemek için")
    @CrossOrigin
    @GetMapping("{postId}/getAllLike")
    public ResponseEntity<List<LikeResponseDTO>> getAllLike(@PathVariable int postId){
        return new ResponseEntity<List<LikeResponseDTO>>(postService.getAllLike(postId),HttpStatus.OK);
    }//Deneme

    @ApiOperation(value = "takip ettiğim kişilerin postlarını görüntülemek için Paging yapılır")
    @CrossOrigin
    @GetMapping
    public ResponseEntity<PostPageList> listPosts(@RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                  @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                  @RequestParam(value = "userId",required = true) String userId,
                                                  @RequestParam(value = "profilePost", required = false) Boolean profilePost
                                                  ){

        if (pageNumber == null || pageNumber < 0){
            pageNumber = DEFAULT_PAGE_NUMBER;
        }

        if (pageSize == null || pageSize < 1) {
            pageSize = DEFAULT_PAGE_SIZE;
        }

        PostPageList postList = postService.listPost(userId,PageRequest.of(pageNumber, pageSize),profilePost);
        return new ResponseEntity<>(postList,HttpStatus.OK);
    }

    @ApiOperation(value = "var olan bir postu takibe almak için")
    @CrossOrigin
    @PostMapping("/{postId}/watch")
    public ResponseEntity<PostResponseDTO> watchPost(@PathVariable int postId,  @RequestParam(value = "userId",required = true) String userId){
        return new ResponseEntity<>(postService.watchPost(postId,userId),HttpStatus.OK);
    }

    @ApiOperation(value = "takibe aldığım bir postun takibini bırakmak için")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{postId}/watch")
    public void deleteWatch(@PathVariable int postId,
                            @RequestParam(value = "userId",required = true) String userId){
        postService.deleteWatch(postId,userId);
    }

    @ApiOperation(value = "takibe aldığım bir postun takibini bırakmak için WEB İCİN GELİŞTİRİLDİ")
    @CrossOrigin
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PostMapping("/{postId}/unWatchPost")
    public void deleteWatchForWeb(@PathVariable int postId,
                            @RequestParam(value = "userId",required = true) String userId){
        postService.deleteWatch(postId,userId);
    }
    @ApiOperation(value = "kullanıcının takip ettiği postları görüntülemesi için")
    @CrossOrigin
    @GetMapping("/{userId}/watchedPost")
    public ResponseEntity<List<PostResponseDTO>> listWatchedPosts(@PathVariable String userId){
        return new ResponseEntity<>(postService.listWatchedPosts(userId),HttpStatus.OK);
    }


}
