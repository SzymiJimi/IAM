package com.inzynieria.insurance.service;


import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Klasa odpowiedzialna za konfiguracje i wysyłanie odpowiednich wiadomości e-mail
 */
public class SendMailService {
    String messageReceiver;
    String messageContent;

    public SendMailService(String messageReceiver, String messageContent){
        this.messageReceiver=messageReceiver;
        this.messageContent=messageContent;
    }

    public void send() {

        final String username = "insurance.application@insurance.com";
        final String password = "fikcja";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "127.0.0.1");
        props.put("mail.smtp.port", "25");
        //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("insurance.application@insurance.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(messageReceiver));
            message.setSubject("Agencja ubezpieczeniowa");
            message.setText(messageContent);

            Transport.send(message);


        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}