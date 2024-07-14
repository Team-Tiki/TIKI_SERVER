package com.tiki.server.mail.config;

import lombok.val;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

import static com.tiki.server.mail.constants.MailConstants.MailPassword;
import static com.tiki.server.mail.constants.MailConstants.TIKI_ADDRESS;

@Configuration
public class MailConfig {
    @Bean
    public JavaMailSender setProperties() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("smtp.gmail.com");
        mailSender.setPort(587);
        mailSender.setUsername(TIKI_ADDRESS);
        mailSender.setPassword(MailPassword);

        val javaMailProperties = getProperties();

        mailSender.setJavaMailProperties(javaMailProperties);

        return mailSender;
    }

    private static Properties getProperties() {
        Properties javaMailProperties = new Properties();
        javaMailProperties.put("mail.transport.protocol", "smtp");
        javaMailProperties.put("mail.smtp.auth", "true");
        javaMailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        javaMailProperties.put("mail.smtp.starttls.enable", "true");
        javaMailProperties.put("mail.debug", "true");
        javaMailProperties.put("mail.smtp.ssl.trust", "smtp.naver.com");
        javaMailProperties.put("mail.smtp.ssl.protocols", "TLSv1.2");
        return javaMailProperties;
    }
}
