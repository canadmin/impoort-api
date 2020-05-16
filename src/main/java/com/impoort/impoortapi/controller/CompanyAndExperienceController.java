package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.StartUpsDto;
import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.service.CompanyAndExperienceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CompanyAndExperienceController {


    private final CompanyAndExperienceService companyAndExperienceService;

    @ApiOperation(value = "kullanıcı adı company name olan userType'ı STARTUP olan kullanıcıların yani şirketlerin listesini döner",
                            response = List.class)
    @GetMapping("company")
    @CrossOrigin
    public ResponseEntity<List<StartUpsDto>> searchCompany(@RequestParam(value = "companyName", required = true) String companyName){
        return new ResponseEntity<>(companyAndExperienceService.findCompanyName(companyName), HttpStatus.OK);
    }

    @ApiOperation(value = "kullanıcıların yeni deneyim eklemesi için kullanılır parametre olarak experience listesi gelir",
                          response =List.class )
    @PostMapping("experience")
    @CrossOrigin
    public ResponseEntity  newExperiences(@RequestBody @Valid List<Experience> experiences){
        return new ResponseEntity<>(companyAndExperienceService.addExperience(experiences),HttpStatus.CREATED);
    }

    @ApiOperation(value = "kullanıcıların deneyimlerini güncellemesi için kullanılır ", response = List.class)
    @PutMapping("experience")
    @CrossOrigin
    public ResponseEntity updateExperiences(@RequestBody @Valid List<Experience> experiences){
        return new ResponseEntity<>(companyAndExperienceService.updateExperience(experiences),HttpStatus.CREATED);
    }

}
