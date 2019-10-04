package com.impoort.impoortapi.repository.message;

import com.impoort.impoortapi.domain.messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByMessageSenderUserIdAndMessageReceiverUserId(String messageSenderUserId,String messageReceiverUserId);

}
