package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserUpdateDto;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.service.UserService;
import com.impoort.impoortapi.utils.MailSenderService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.Response;
import java.util.List;

@Controller
@RequestMapping("/api/v1/user")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Autowired
    public JavaMailSender emailSender;

    public UserController(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>>  getAllUser(){
          return new ResponseEntity<List<UserResponseDTO>>(userService.getAllUser(), HttpStatus.OK);
    }
    /*
    kullanıcı bilgilerini güncellemek için yazıldı
    */
    @CrossOrigin
    @PostMapping("/updateUser")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody UserUpdateDto user){
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }

    @CrossOrigin
    @GetMapping("/{userId}/{myId}")
    public ResponseEntity<UserResponseDTO> getUser(@PathVariable String userId,@PathVariable String myId){
    return new ResponseEntity<>(userService.getUser(userId,myId),HttpStatus.OK);
    }

    /*
    @GetMapping("/verifyAccount/url/{activeGuide}")
    public String verifyAccount(@PathVariable(value = "activeGuide", required = true) String activeGuide ){
        String resultMessage = "Bir hata meydana geldi, lütfen tekrar deneyin.";
        UserResponseDTO userResponseDTO = userService.findByActiveGuide(activeGuide);
        if(userResponseDTO != null) {
            if (!userResponseDTO.isActive()) {
                userResponseDTO.setActive(true);
                userService.updateUser(userResponseDTO);
                resultMessage = "Hesabınız başarıyla onaylandı.";

            } else {
                resultMessage = "Hesabınız zaten onaylanmış.";
            }
        }
        return resultMessage;
    }*/

}
/* Mail Sender Test
        MailSenderService mailSenderService = new MailSenderService(emailSender);
        mailSenderService.sendSimpleMessage("yusufali.cezik@hotmail.com", "", false, "Yusuf Ali Çezik");*/
