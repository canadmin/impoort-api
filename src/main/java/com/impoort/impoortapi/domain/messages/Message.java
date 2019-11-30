package com.impoort.impoortapi.domain.messages;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Null;

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

    @Null
    @JsonFormat(pattern="dd-MM-yyyy hh:mm:ss", shape=JsonFormat.Shape.STRING)
    private long messageDate;


}
