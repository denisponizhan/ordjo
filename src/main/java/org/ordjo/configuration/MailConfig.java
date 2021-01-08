package org.ordjo.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

    /*
    play.mailer.ssl = no
    play.mailer.tls = yes
    */

    @Value("${play.mailer.host}")
    private String host;

    @Value("${play.mailer.port}")
    private Integer port;

    @Value("${play.mailer.user}")
    private String user;

    @Value("${play.mailer.password}")
    private String password;

    @Bean
    public JavaMailSender mailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(host);
        mailSender.setPort(port);
        mailSender.setUsername(user);
        mailSender.setPassword(password);

        return mailSender;
    }
}
