package com.lzheng.coolpan.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;


/**
 * @ClassName MailService
 * @Author 刘正
 * @Date 2019/12/16 16:48
 * @Version 1.0
 * @Description:
 */

@Service
public class MailService {

    @Value("${mail.from}")
    private String from;

    @Value("${spring.mail.username}")
    private String fromMail;

    @Value("${mail.subject}")
    private String subject;

    @Value("${mail.content}")
    private String content;

    @Autowired
    private JavaMailSender mailSender;

    public void sendSimpleMail(String to) throws UnsupportedEncodingException {
        SimpleMailMessage message = new SimpleMailMessage();
        String fromByte = new String((from + " <" + fromMail + ">")
                .getBytes("UTF-8"));
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content+"www.lzhengycy.club");
        message.setFrom(fromByte);
        mailSender.send(message);

    }

}