package com.impoort.impoortapi.service;

import com.impoort.impoortapi.api.v1.model.responsemodel.UserMessageDTO;
import com.impoort.impoortapi.domain.messages.Message;
import com.impoort.impoortapi.domain.messages.MessagesGeneral;
import com.impoort.impoortapi.domain.user.User;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessageWithReceiver(String senderId,String receiverId);


    Message sendMessageToReceiver(Message message);

    List<UserMessageDTO> getAllMessageUser(String userId);

}
