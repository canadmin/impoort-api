package com.impoort.impoortapi.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

public class MailSenderService {
    private JavaMailSender emailSender;

    public MailSenderService(JavaMailSender emailSender){
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage(String mail, String text, Boolean isActive, String name) {
        if (!isActive) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail);
            message.setSubject("Hesap Onayı");
            message.setText("Merhaba " + name.toUpperCase() + " , \n " +
                    "Lütfen hesabını onaylamak aşağıdaki linke tıkla \n \n " +
                    "localhost:8081/api/users/verifyAcount/url/" + text);
            emailSender.send(message);
        }

    }

}
