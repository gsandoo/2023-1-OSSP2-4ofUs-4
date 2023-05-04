package com.example.cokkiri.service;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class MailAuthConfiguration {
    @Bean(name="mailSender")
    public JavaMailSenderImpl getJavaMailSender() {
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.starttls.required", true);
        properties.put("mail.debug", true);
        properties.put("mail.smtp.ssl.protocols", "TLSv1.2");

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername("skxkswls@gmail.com");
        mailSender.setPassword("ykwgldlnnqcrbplh");
        mailSender.setDefaultEncoding("utf-8");
        mailSender.setJavaMailProperties(properties);

        return mailSender;
       }
}
