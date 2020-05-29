package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.SearchRequest;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.service.SearchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/api/v1/search")
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @ApiOperation(value = "kullanıcı aramak için kullanılır")
    @CrossOrigin
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> searchUser(@RequestBody @Valid SearchRequest searchRequest){
        return new ResponseEntity<>(searchService.getSearchUser(searchRequest), HttpStatus.OK);
    }
    @ApiOperation(value = "Sadece Web için kullanılacak")
    @CrossOrigin
    @PostMapping
    public ResponseEntity<List<UserResponseDTO>> searchUserForWeb(@RequestBody @Valid SearchRequest searchRequest){
        return new ResponseEntity<>(searchService.getSearchUser(searchRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Sadece IOS için kullanılacak")
    @CrossOrigin
    @GetMapping("/ios")
    public ResponseEntity<List<UserResponseDTO>> searchUserForIos(
            @RequestParam(value = "fullName", required = true) String fullName,
            @RequestParam(value = "userType" , required = true) String[] userTypes
    ){
        SearchRequest searchRequest= new SearchRequest();
        searchRequest.setFullName(fullName);
        List<UserType> userTypeList = new ArrayList<>();
        for (int i = 0; i <userTypes.length ; i++) {
            if(userTypes[i].equalsIgnoreCase("investor")){
                userTypeList.add(UserType.INVESTOR);
            }else if(userTypes[i].equalsIgnoreCase("normal_user")){
                userTypeList.add(UserType.NORMAL_USER);
            }else if(userTypes[i].equalsIgnoreCase("developer")){
                userTypeList.add(UserType.NORMAL_USER);
            }else if(userTypes[i].equalsIgnoreCase("startup")){
                userTypeList.add(UserType.STARTUP);
            }
        }
        searchRequest.setUserTypes(userTypeList);
        return new ResponseEntity<>(searchService.getSearchUser(searchRequest), HttpStatus.OK);
    }

}
