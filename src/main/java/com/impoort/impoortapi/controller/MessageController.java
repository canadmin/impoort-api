package com.impoort.impoortapi.controller;

import com.impoort.impoortapi.api.v1.model.responsemodel.UserMessageDTO;
import com.impoort.impoortapi.domain.messages.Message;
import com.impoort.impoortapi.domain.messages.MessagesGeneral;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.service.MessageService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/api/v1/messages")
public class MessageController {

    private MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation(value = "iki kullanıcı arasındaki mesajların hepsini döner")
    @CrossOrigin
    @GetMapping("/allMessage/{senderId}/{receiverId}")
    public ResponseEntity<List<Message>> getAllMessageWithReceiverUser(@PathVariable String senderId, @PathVariable String receiverId){
        return new ResponseEntity<>(messageService.getAllMessageWithReceiver(senderId,receiverId), HttpStatus.OK);
    }

    @ApiOperation("bir kullanıcıya mesaj göndermek için")
    @CrossOrigin
    @PostMapping("/sendMessage")
    public ResponseEntity<Message> sendNewMessageToReceiver(@RequestBody @Valid Message message){
        return  new ResponseEntity<Message>(messageService.sendMessageToReceiver(message),HttpStatus.OK);
    }

    @ApiOperation("bir kullanıcının diğer kullanıcı ile ")
    @CrossOrigin
    @GetMapping("/inbox/{userId}")
    public ResponseEntity<List<UserMessageDTO>> getUserMessagesUsers(@PathVariable String userId){
        return new ResponseEntity<List<UserMessageDTO>>(messageService.getAllMessageUser(userId),HttpStatus.OK);
    }


}
