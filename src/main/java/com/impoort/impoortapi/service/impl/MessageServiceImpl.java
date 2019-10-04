package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.domain.messages.Message;
import com.impoort.impoortapi.domain.messages.MessagesGeneral;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.MessageGeneralRepository;
import com.impoort.impoortapi.repository.MessageRepository;
import com.impoort.impoortapi.repository.UserRepository;
import com.impoort.impoortapi.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.*;

@Service
public class MessageServiceImpl implements MessageService {

    private UserRepository userRepository;

    private final MessageRepository messageRepository;
    private MessageGeneralRepository messageGeneralRepository;

    @Autowired
    public MessageServiceImpl(UserRepository userRepository, MessageRepository messageRepository, MessageGeneralRepository messageGeneralRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.messageGeneralRepository = messageGeneralRepository;
    }

    @Override
    public List<Message> getAllMessageWithReceiver(String senderId, String receiverId) {
        List<Message> firstMessageList=messageRepository.findAllByMessageSenderUserIdAndMessageReceiverUserId(senderId,receiverId);
        List<Message> secondMessageList=messageRepository.findAllByMessageSenderUserIdAndMessageReceiverUserId(receiverId,senderId);
        for(int i=0;i<secondMessageList.size(); i++){
            firstMessageList.add(secondMessageList.get(i));
        }
        firstMessageList.sort(Comparator.comparing(Message::getMessageDate));


        return firstMessageList;
    }


    @Override
    public Message sendMessageToReceiver(Message message) {
        saveMessageGeneral(message);
        return messageRepository.save(message);
    }


    private void saveMessageGeneral(Message message){

        MessagesGeneral messagesGeneral = new MessagesGeneral();
        messagesGeneral.setUserId(message.getMessageSenderUserId());
        messagesGeneral.setUserMessagesWithID(message.getMessageReceiverUserId());
        messagesGeneral.setLastMessage(message.getMessageText());

        MessagesGeneral messagesGeneral2 = new MessagesGeneral();
        messagesGeneral2.setUserId(message.getMessageReceiverUserId());
        messagesGeneral2.setUserMessagesWithID(message.getMessageSenderUserId());
        messagesGeneral2.setLastMessage(message.getMessageText());

        MessagesGeneral messagesGeneralExist = messageGeneralRepository.findByUserIdAndUserMessagesWithID(message.getMessageSenderUserId(),message.getMessageReceiverUserId());
        MessagesGeneral messagesGeneralExist2 = messageGeneralRepository.findByUserIdAndUserMessagesWithID(message.getMessageReceiverUserId(),message.getMessageSenderUserId());
        if(messagesGeneralExist ==null ){
            messageGeneralRepository.save(messagesGeneral);
        }else{
            messagesGeneral.setMessageGeneralId(messagesGeneralExist.getMessageGeneralId());
            messageGeneralRepository.save(messagesGeneral);
        }
        if(messagesGeneralExist2 == null) {
            messageGeneralRepository.save(messagesGeneral2);
        }else{
            messagesGeneral2.setMessageGeneralId(messagesGeneralExist2.getMessageGeneralId());
            messageGeneralRepository.save(messagesGeneral2);
        }

    }

    @Override
    public List<User> getAllMessageUser(String userId){
        List<MessagesGeneral> messagesGenerals= messageGeneralRepository.findAllByUserId(userId);
        List<User> returnedUser=new ArrayList<>();
        for(int i = 0; i < messagesGenerals.size() ; i++){
            User user= userRepository.getOne(messagesGenerals.get(i).getUserMessagesWithID());
            returnedUser.add(user);
        }
        return returnedUser;
    }
}
