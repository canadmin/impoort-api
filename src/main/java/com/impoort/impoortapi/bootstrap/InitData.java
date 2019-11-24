package com.impoort.impoortapi.bootstrap;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InitData implements CommandLineRunner {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    @Override
    public void run(String... args) throws Exception {

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("hasan")
                        .email("hasan")
                        .password("123")
                        .build(),User.class));

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("can")
                        .email("can")
                        .password("123")
                        .build(),User.class));

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("burak")
                        .email("burak")
                        .password("123")
                        .build(),User.class));

        //Startup hesabı

        userRepository.save(modelMapper
                .map(UserRequestDTO.builder()
                        .firstName("facebook")
                        .email("facebook")
                        .password("123").userType(2)
                        .build(),User.class));
    }
}
