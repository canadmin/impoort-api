package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.domain.pageLists.WatcherPageList;
import com.impoort.impoortapi.domain.pageLists.WatchingPageList;
import com.impoort.impoortapi.domain.watch.Watch;
import com.impoort.impoortapi.service.WatchService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@Controller
@RequestMapping("/api/v1/watch")
public class WatchController {
    private final WatchService watchService;
    @Autowired
    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    /**
     *
     * @param watcherId takip edecenin id si
     * @param watchingId takip edileceğin id si
     * @return takip edenin id si ile takip edilenin id si dönüyor önemli bişey değil
     */
    @ApiOperation(value = "bir kullanıcıyı takip etmek için")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<Watch> watchUser( @RequestParam(value = "watcherId", required = false) String watcherId,
                                            @RequestParam(value = "watchingId", required = false) String watchingId){
        return new ResponseEntity<Watch>(watchService.watchUser(watcherId,watchingId), HttpStatus.OK) ;
    }

    /**
     *
     * @param watchingId bu id hergangi bir kullanıcının id değil
     *                   watchingId iki kişi arasındaa oluşan takibin id sidir bunun
     *                   silinmesi durumunda takip eden kışı takip ettiği kişiyi
     *                   takipten çıkartır.
     */
    @ApiOperation(value = "value takibi durdurmak için")
    @CrossOrigin
    @DeleteMapping("/{watchingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void stopWatching(@PathVariable int watchingId){
        watchService.stopWatching(watchingId);
    }

    @ApiOperation(value = "kullanıcının kendisini takip edenleri görüntülemek için paging yapıldı")
    @CrossOrigin
    @GetMapping("/watcher/{userId}")
    public ResponseEntity<WatcherPageList> getWatcher(@PathVariable String userId,
                                                      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize,
                                                      @RequestParam(value = "myId", required = true) String myId){

        return new ResponseEntity<>(watchService.getWatcher(userId,myId, PageRequest.of(pageNumber,pageSize)),HttpStatus.OK);
    }

    @ApiOperation(value = "kullanıcın takip ettiklerini görüntülemek için paging yapıldı")
    @CrossOrigin
    @GetMapping("/watching/{userId}")
    public ResponseEntity<WatchingPageList> getWatching(@PathVariable String userId,
                                                        @RequestParam(value = "myId", required = true) String myId,
                                                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", required = false) Integer pageSize){


        return new ResponseEntity<>(watchService.getWatching(userId,myId,PageRequest.of(pageNumber,pageSize)),HttpStatus.OK);
    }

}
