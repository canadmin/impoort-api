package com.impoort.impoortapi.domain.messages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int messageId;
    private String messageSenderUserId;
    private String messageReceiverUserId;
    private String messageText;
    private long messageDate;


}
