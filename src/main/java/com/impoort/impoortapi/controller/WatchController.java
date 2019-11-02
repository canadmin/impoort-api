package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.domain.watch.Watch;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.service.WatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}
