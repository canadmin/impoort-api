package com.impoort.impoortapi.bootstrap;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.domain.enums.UserType;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

//@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public void run(String... args) throws Exception {

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("hasan")
                        .lastName("cerit")
                        .email("hasan")
                        .password("123")
                        .userType(UserType.STARTUP)
                        .build(),User.class));

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("can")
                        .lastName("yard")
                        .email("can")
                        .password("123")
                        .build(),User.class));

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("burak")
                        .lastName("deneme")
                        .email("burak")
                        .password("123")
                        .build(),User.class));

        //Startup hesabı

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("facebook")
                        .email("facebook")
                        .password("123").userType(UserType.DEVELOPER)
                        .build(),User.class));
    }
}
