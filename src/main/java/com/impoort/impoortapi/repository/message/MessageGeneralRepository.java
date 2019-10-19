package com.impoort.impoortapi.repository.message;

import com.impoort.impoortapi.domain.messages.MessagesGeneral;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageGeneralRepository extends JpaRepository<MessagesGeneral,Integer> {

    MessagesGeneral findByUserIdAndUserMessagesWithID(String userId,String userMessageWithID);

    List<MessagesGeneral> findAllByUserId(String userId);
}

