package com.impoort.impoortapi.controller.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("error/")
public class ErrorController {

    @CrossOrigin
    @GetMapping("invalid_auth")
    public Map<String,String> invalidToken(){
        HashMap<String,String> invalidToken = new HashMap<>();
        invalidToken.put("401","UNAUTHORIZED");
        invalidToken.put("message","invalid token || public_key");
        return  invalidToken;
    }

}
