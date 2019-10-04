package com.impoort.impoortapi.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessagesGeneral {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageGeneralId;
    private String userId;
    private String userMessagesWithID;
    private String lastMessage;

    public MessagesGeneral(String userId, String userMessagesWithID, String lastMessage) {
        this.userId = userId;
        this.userMessagesWithID = userMessagesWithID;
        this.lastMessage = lastMessage;
    }
}
