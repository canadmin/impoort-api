package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserUpdateDto;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.company.Experience;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.repository.company.CompanyRepository;
import com.impoort.impoortapi.service.UserService;
import com.impoort.impoortapi.utils.Converters;
import com.impoort.impoortapi.utils.RandomStringGenerator;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    /**
     * @param userId profili görüntülenen kullanıcının id'si
     * @param myId   profili görüntüleyen kullanıcının id'si bu silinecek gerek yok
     * @return
     */
    @Override
    public UserResponseDTO getUser(String userId, String myId) {
        UserResponseDTO userResponseDTO = modelMapper.map(userRepository.getOne(userId), UserResponseDTO.class);
        if (userResponseDTO.getUserType() == UserType.STARTUP) { //eğer startup hesabı ise company repo dan şirkette çalışan insanların bilgisini getirmeli
            List<Experience> workers = companyRepository.
                    findAllByCompanyIdAndStillWork(userResponseDTO.getUserId(), true);
            List<UserResponseDTO> workerUsers = new ArrayList<>();

            workers.forEach(worker -> workerUsers.add(modelMapper
                    .map(userRepository.getOne(worker.getWorkerId()), UserResponseDTO.class)));

            userResponseDTO.setEmployees(workerUsers);
        }
        userResponseDTO.setExperiences(companyRepository.findByWorkerId(userId));
        return userResponseDTO;
    }


    //test için yazıldı silinecek
    @Override
    public List<UserResponseDTO> getAllUser() {
        List<User> userList = userRepository.findAll();
        List<UserResponseDTO> userResponseDTOS = Arrays.asList(modelMapper.map(userList, UserResponseDTO[].class));
        return userResponseDTOS;
    }

    //mail doğrulaması
    @Override
    public UserResponseDTO findByActiveGuide(String activeGuide) {
        UserResponseDTO userResponseDTO = modelMapper.map(userRepository
                .findByActiveGuide(activeGuide), UserResponseDTO.class);
        return userResponseDTO;
    }

    //ne amaçla yazılmış bilemiyorum
    @Override
    public UserResponseDTO updateUser(UserResponseDTO userResponseDTO) {
        User updatedUser = modelMapper.map(userResponseDTO, User.class);
        updatedUser.setFirstName(Converters.generateFullName(updatedUser.getFirstName(),updatedUser.getLastName()));
        return modelMapper.map(userRepository.save(updatedUser), UserResponseDTO.class);
    }


    //bu kullanıcının profil bilgilerinin güncellenmesi için yazıldı
    @Override
    @Transactional
    public UserResponseDTO updateUser(UserUpdateDto userUpdateDto) {
        User userDb = userRepository.getOne(userUpdateDto.getUserId());
        User updatedUser = (modelMapper.map(userUpdateDto, User.class));

        System.out.println(userDb.getUserId());

        updatedUser.setFullName(Converters.generateFullName(userUpdateDto.getFirstName(),userUpdateDto.getLastName()));

        userDb.setFullName(updatedUser.getFullName());
        userDb.setDepartment(userUpdateDto.getDepartment());
        userDb.setDescription(userUpdateDto.getDescription());
        userDb.setLinks(userUpdateDto.getLinks());
        userDb.setFirstName(updatedUser.getFirstName());
        userDb.setLastName(updatedUser.getLastName());
        userDb.setPhoneNumber(updatedUser.getPhoneNumber());
        userDb.setCity(updatedUser.getCity());
        userDb.setEmail(updatedUser.getEmail());
        userDb.setUserType(updatedUser.getUserType());
        userDb.setGender(userUpdateDto.getGender());
        userDb.setProfileImgUrl(userUpdateDto.getProfileImgUrl());


        userRepository.save(userDb);
        UserResponseDTO userResponseDTO = modelMapper.map(updatedUser, UserResponseDTO.class);
        userResponseDTO.setExperiences(companyRepository.findByWorkerId(userResponseDTO.getUserId()));
        return userResponseDTO;
    }

    @Override
    public void updateUserProfileImg(String userId, String url) {
        User user = userRepository.getOne(userId);
        user.setProfileImgUrl(url);
        userRepository.save(user);
    }
}
