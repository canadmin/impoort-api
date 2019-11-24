package com.impoort.impoortapi.service;


import com.impoort.impoortapi.api.v1.model.requestmodel.StartUpsDto;
import com.impoort.impoortapi.domain.company.Experience;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CompanyAndExperienceService {

    List<StartUpsDto> findCompanyName(String companyName);

    ResponseEntity addExperience(List<Experience> experience);

    ResponseEntity updateExperience(List<Experience> experiences);
}
