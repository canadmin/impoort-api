package com.impoort.impoortapi.repository;

import com.impoort.impoortapi.domain.messages.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.NamedQuery;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message,Integer> {
    List<Message> findAllByMessageSenderUserIdAndMessageReceiverUserId(String messageSenderUserId,String messageReceiverUserId);

}
