package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserUpdateDto;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.company.CompanyRepository;
import com.impoort.impoortapi.service.UserService;
import com.impoort.impoortapi.utils.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CompanyRepository companyRepository;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CompanyRepository companyRepository) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.companyRepository = companyRepository;
    }

    @Override
    public UserResponseDTO getUser(String userId) {

        UserResponseDTO userResponseDTO = modelMapper.map(userRepository.getOne(userId),UserResponseDTO.class);
        if(userResponseDTO.getUserType() == 2){
            List<Experience> workers=companyRepository.
                    findAllByCompanyIdAndStillWork(userResponseDTO.getUserId(),true);
            List<UserResponseDTO> workerUsers = new ArrayList<>();
            for(Experience worker : workers){
                try {
                    UserResponseDTO userResponseDTO1 = modelMapper
                            .map(userRepository.getOne(worker.getWorkerId()), UserResponseDTO.class);
                    workerUsers.add(userResponseDTO1);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            userResponseDTO.setEmployees(workerUsers);
        }
        System.out.println(userResponseDTO.getExperiences());
        return userResponseDTO;
    }

    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> userList=userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS= Arrays.asList(modelMapper.map(userList,UserResponseDTO[].class));
        return userResponseDTOS;
    }



    @Override
    public UserResponseDTO findByActiveGuide(String activeGuide) {
        UserResponseDTO userResponseDTO = modelMapper.map(userRepository
                .findByActiveGuide(activeGuide), UserResponseDTO.class);
        return userResponseDTO;
    }

    @Override
    public UserResponseDTO updateUser(UserResponseDTO userResponseDTO) {
        User updatedUser = modelMapper.map(userResponseDTO, User.class);
        return modelMapper.map(userRepository.save(updatedUser), UserResponseDTO.class);
    }

    @Override
    public UserResponseDTO updateUser(UserUpdateDto userUpdateDto) {
        List<Experience> experiences=userUpdateDto.getExperiences();
        for (Experience exp : experiences){
            Experience newExp;
            newExp=exp;
            newExp.setWorkerId(userUpdateDto.getUserId());
            companyRepository.save(newExp);
        }

        User updatedUser =(modelMapper.map(userUpdateDto,User.class));
        User oldUser = userRepository.getOne(userUpdateDto.getUserId());

        User newUser = updatedUser;
        newUser.setActiveGuide(oldUser.getActiveGuide());
        newUser.setWatcherCount(oldUser.getWatcherCount());
        newUser.setWatchingCount(oldUser.getWatchingCount());
        newUser.setWatchingPostCount(oldUser.getWatchingPostCount());
        newUser.setEmployeeCount(oldUser.getEmployeeCount());
        newUser.setActive(oldUser.isActive());

        userRepository.save(newUser);
        UserResponseDTO userResponseDTO=modelMapper.map(newUser,UserResponseDTO.class);
        userResponseDTO.setExperiences(companyRepository.findByWorkerId(userResponseDTO.getUserId()));
        return userResponseDTO;
    }
}
