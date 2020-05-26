package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.requestmodel.UserRequestDTO;
import com.impoort.impoortapi.api.v1.model.requestmodel.UserUpdateDto;
import com.impoort.impoortapi.api.v1.model.responsemodel.UserResponseDTO;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.domain.watch.Watcher;
import com.impoort.impoortapi.domain.watch.Watching;
import com.impoort.impoortapi.service.UserService;
import com.impoort.impoortapi.utils.MailSenderService;
import io.swagger.annotations.ApiOperation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    //test için var  silinecek
    @ApiOperation(value = "Bunu kullanmayın test için yazılmıştı")
    @CrossOrigin
    @GetMapping("/users")
    public ResponseEntity<List<UserResponseDTO>> getAllUser(){
          return new ResponseEntity<List<UserResponseDTO>>(userService.getAllUser(), HttpStatus.OK);
    }
    /*
    kullanıcı bilgilerini güncellemek için yazıldı
    */
    @ApiOperation(value = "kullanıcın bilgilerini güncellemek için")
    @CrossOrigin
    @PostMapping("/updateUser")
    public ResponseEntity<UserResponseDTO> updateUser(@RequestBody @Valid UserUpdateDto user){
        return new ResponseEntity<>(userService.updateUser(user),HttpStatus.OK);
    }


    // profile görüntülemek için yazıldı
    @ApiOperation(value = "diğer bir kullanıcının profilini görüntülemek için")
    @CrossOrigin
    @GetMapping("/profile/{userId}")
    public ResponseEntity<UserResponseDTO> getUser(
            @RequestParam(value = "myId", required = true) String myId, @PathVariable String userId){
    return new ResponseEntity<>(userService.getUser(userId,myId),HttpStatus.OK);
    }

    @ApiOperation(value = "kullanıcı fotoğrafı eklemek ve güncellemek için")
    @CrossOrigin
    @PostMapping("/updateProfileImg")
    public ResponseEntity<String> updateProfileImg(@RequestParam(value = "userId", required = true) String userId,
                                           @RequestParam(value = "url" , required =  true)String url){
            this.userService.updateUserProfileImg(userId,url);

            return new ResponseEntity<>("profile image updated successfully",HttpStatus.CREATED);
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
