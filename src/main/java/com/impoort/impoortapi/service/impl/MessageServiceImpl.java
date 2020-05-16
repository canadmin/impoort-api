package com.impoort.impoortapi.service.impl;

import com.impoort.impoortapi.api.v1.model.responsemodel.UserMessageDTO;
import com.impoort.impoortapi.domain.messages.Message;
import com.impoort.impoortapi.domain.messages.MessagesGeneral;
import com.impoort.impoortapi.domain.user.User;
import com.impoort.impoortapi.repository.message.MessageGeneralRepository;
import com.impoort.impoortapi.repository.message.MessageRepository;
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
    public MessageServiceImpl(UserRepository userRepository,
                              MessageRepository messageRepository,
                              MessageGeneralRepository messageGeneralRepository) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.messageGeneralRepository = messageGeneralRepository;
    }

    @Override
    public List<Message> getAllMessageWithReceiver(String senderId, String receiverId) {
        List<Message> firstMessageList = messageRepository
                .findAllByMessageSenderUserIdAndMessageReceiverUserId(senderId, receiverId);
        List<Message> secondMessageList = messageRepository
                .findAllByMessageSenderUserIdAndMessageReceiverUserId(receiverId, senderId);
        firstMessageList.addAll(secondMessageList);
        firstMessageList.sort(Comparator.comparing(Message::getMessageDate));
        return firstMessageList;
    }


    @Override
    public Message sendMessageToReceiver(Message message) {
        saveMessageGeneral(message);
        return messageRepository.save(message);
    }


    private void saveMessageGeneral(Message message) {

        MessagesGeneral messagesGeneralFromMe = new MessagesGeneral(message.getMessageSenderUserId(),
                message.getMessageReceiverUserId(),
                message.getMessageText());

        MessagesGeneral messagesGeneralToOther = new MessagesGeneral(message.getMessageReceiverUserId(),
                message.getMessageSenderUserId(),
                message.getMessageText());

        MessagesGeneral messagesGeneralExistMe = messageGeneralRepository
                .findByUserIdAndUserMessagesWithID(message.getMessageSenderUserId(),
                        message.getMessageReceiverUserId());

        MessagesGeneral messagesGeneralExistOther = messageGeneralRepository
                .findByUserIdAndUserMessagesWithID(message.getMessageReceiverUserId(),
                        message.getMessageSenderUserId());

        if (messagesGeneralExistMe == null) {
            messageGeneralRepository.save(messagesGeneralFromMe);
        } else {
            messagesGeneralFromMe
                    .setMessageGeneralId(messagesGeneralExistMe.getMessageGeneralId());
            messageGeneralRepository.save(messagesGeneralFromMe);
        }
        if (messagesGeneralExistOther == null) {
            messageGeneralRepository.save(messagesGeneralToOther);
        } else {
            messagesGeneralToOther
                    .setMessageGeneralId(messagesGeneralExistOther.getMessageGeneralId());
            messageGeneralRepository.save(messagesGeneralToOther);
        }

    }

    /**
     *
     * @param userId fixlenebilir çok eskiden yazdık mantığını unuttum
     * @return
     */
    @Override
    public List<UserMessageDTO> getAllMessageUser(String userId) {
        List<MessagesGeneral> messagesGenerals = messageGeneralRepository
                .findAllByUserId(userId);
        List<UserMessageDTO> returnedUser = new ArrayList<>();
        for (MessagesGeneral msg : messagesGenerals) {
            User user = userRepository.getOne(msg.getUserMessagesWithID());
            UserMessageDTO userMessageDTO = new
                    UserMessageDTO(user.getUserId(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getProfileImgUrl(),
                    msg.getLastMessage());

            returnedUser.add(userMessageDTO);
        }
        return returnedUser;
    }
}
