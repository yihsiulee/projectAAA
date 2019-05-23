package com.allpass.projectAAA.Mail;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
public class EmailService {

    @Resource
    private JavaMailSender emailSender;

    @Resource
    private SpringTemplateEngine templateEngine;


    public void sendSimpleMessage(Mail mail) throws MessagingException, IOException {
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message,
                MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                StandardCharsets.UTF_8.name());

//        helper.addAttachment("logo.png", new ClassPathResource("memorynotfound-logo.png"));

        Context context = new Context();
        context.setVariables(mail.getModel());
//        String html = templateEngine.process("email-template", context);
        String html = templateEngine.process("email-template", context);


        helper.setTo(mail.getTo());
        helper.setText(html, true);
        helper.setSubject(mail.getSubject());
        helper.setFrom(mail.getFrom());

        emailSender.send(message);
    }

}