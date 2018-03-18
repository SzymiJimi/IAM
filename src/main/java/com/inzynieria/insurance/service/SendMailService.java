package com.inzynieria.insurance.service;


import java.io.UnsupportedEncodingException;
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
    /**
     * Odbioca wiadomości
     */
    String messageReceiver;
    /**
     * Treść wiadomości
     */
    String messageContent;

    String subject;

    public SendMailService(String messageReceiver, String messageContent, String messageTitle){
        this.messageReceiver=messageReceiver;
        this.messageContent=messageContent;
        this.subject=messageTitle;
    }

    /**
     * Metoda wysyłająca wiadomość e-mail
     */
    public void send() {

//        final String username = "insurance.application@insurance.com";
//        final String password = "fikcja";
        final String username = "rekas1@tlen.pl";
        final String password = "Rekas:2@";

//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "127.0.0.1");
//        props.put("mail.smtp.port", "25");
        //props.put("mail.smtp.ssl.trust", "smtp.gmail.com");

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "poczta.o2.pl");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("insurance.application@insurance.com"));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(messageReceiver));
//            message.setSubject("Agencja ubezpieczeniowa");
//            message.setText(messageContent);

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, "Agencja ubezpieczen"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(messageReceiver));
            message.setSubject(subject);
            message.setText(messageContent);

            Transport.send(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}