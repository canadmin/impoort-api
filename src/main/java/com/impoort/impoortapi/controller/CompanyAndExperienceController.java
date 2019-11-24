package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.StartUpsDto;
import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.service.CompanyAndExperienceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class CompanyAndExperienceController {


    private final CompanyAndExperienceService companyAndExperienceService;

    @GetMapping("company")
    @CrossOrigin
    public ResponseEntity<List<StartUpsDto>> searchCompany(@RequestParam(value = "companyName", required = true) String companyName){
        return new ResponseEntity<>(companyAndExperienceService.findCompanyName(companyName), HttpStatus.OK);
    }

    @PostMapping("experience")
    @CrossOrigin
    public ResponseEntity  newExperiences(@RequestBody List<Experience> experiences){
        return new ResponseEntity<>(companyAndExperienceService.addExperience(experiences),HttpStatus.CREATED);
    }

    @PutMapping("experience")
    @CrossOrigin
    public ResponseEntity updateExperiences(@RequestBody List<Experience> experiences){
        return new ResponseEntity<>(companyAndExperienceService.updateExperience(experiences),HttpStatus.CREATED);
    }

}
