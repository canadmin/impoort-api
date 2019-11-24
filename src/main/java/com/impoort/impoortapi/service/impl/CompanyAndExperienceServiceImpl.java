package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.StartUpsDto;
import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.company.CompanyRepository;
import com.impoort.impoortapi.service.CompanyAndExperienceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyAndExperienceServiceImpl implements CompanyAndExperienceService {

    private final CompanyRepository companyRepository;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;


    @Override
    public List<StartUpsDto> findCompanyName(String companyName) {
        List<StartUpsDto> startUpUser;
            if(companyName == null){
                List<User> userList = userRepository.findAllByUserType(2);
                startUpUser = Arrays.asList(modelMapper.map(userRepository.findAllByUserType(2), StartUpsDto[].class));
            }else {
                startUpUser = Arrays.asList(modelMapper.map(userRepository.findAllByFirstNameAndUserType(companyName,2), StartUpsDto[].class));

            }
        return Arrays.asList(modelMapper.map(startUpUser, StartUpsDto[].class));
    }

    @Override
    public ResponseEntity addExperience(List<Experience> experiences) {


        for (Experience exp : experiences) { ;
            companyRepository.save(exp);
        }
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateExperience(List<Experience> experiences) {
        for (Experience exp : experiences) { ;
            companyRepository.save(exp);
        }
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
