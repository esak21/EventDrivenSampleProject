package com.learning.backend.emailService.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @Autowired
    private JavaMailSender mailsender;
    public void sendEmail(String toEmail, String body, String subject, String Attachment) throws MessagingException {
        MimeMessage message = mailsender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setFrom("spring001mailer#gmail.com");
        helper.setTo(toEmail);
        helper.setText(body);
        helper.setSubject(subject);

        String htmlContent = "<h1>WE have Received an Order </h1>" +
                "<p>It can contain <strong>HTML</strong> content.</p>";



        message.setContent(htmlContent, "text/html; charset=utf-8");


//        To attach a File

//        FileSystemResource fileSystem = new FileSystemResource( new File(Attachment));
//        mimemessagehelper.addAttachment(fileSystem.getFilename(), fileSystem);



        mailsender.send(message);


    }
}
