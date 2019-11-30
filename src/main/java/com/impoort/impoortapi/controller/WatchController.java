package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.WatcherPageList;
import com.impoort.impoortapi.api.v1.model.requestmodel.pageLists.WatchingPageList;
import com.impoort.impoortapi.domain.watch.Watch;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.service.WatchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/api/v1/watch")
public class WatchController {
    private final WatchService watchService;
    @Autowired
    public WatchController(WatchService watchService) {
        this.watchService = watchService;
    }

    @CrossOrigin
    @PostMapping
    public ResponseEntity<Watch> watchUser(@RequestBody Watch watch){
        return new ResponseEntity<Watch>(watchService.watchUser(watch), HttpStatus.OK) ;
    }

    @CrossOrigin
    @DeleteMapping("/{watchingId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void stopWatching(@PathVariable int watchingId){
        watchService.stopWatching(watchingId);
    }

    @CrossOrigin
    @GetMapping("/{userId}/{myId}/watcher")
    public ResponseEntity<WatcherPageList> getWatcher(@PathVariable String userId, @PathVariable String myId,
                                                      @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                      @RequestParam(value = "pageSize", required = false) Integer pageSize){

        return new ResponseEntity<>(watchService.getWatcher(userId,myId, PageRequest.of(pageNumber,pageSize)),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{userId}/{myId}/watching")
    public ResponseEntity<WatchingPageList> getWatching(@PathVariable String userId, @PathVariable String myId,
                                                        @RequestParam(value = "pageNumber", required = false) Integer pageNumber,
                                                        @RequestParam(value = "pageSize", required = false) Integer pageSize){


        return new ResponseEntity<>(watchService.getWatching(userId,myId,PageRequest.of(pageNumber,pageSize)),HttpStatus.OK);
    }
}
