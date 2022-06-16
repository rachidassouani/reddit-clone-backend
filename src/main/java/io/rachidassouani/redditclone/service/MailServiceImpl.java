package io.rachidassouani.redditclone.service;

import io.rachidassouani.redditclone.util.Constant;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
public class MailServiceImpl implements MailService {

    private final static Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);
    private final JavaMailSender mailSender;

    @Override
    @Async
    public void sendMail(String to, String body) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(body, true);
            helper.setTo(to);
            helper.setSubject(Constant.NOTIFICATION_EMAIL_SUBJECT);
            helper.setFrom(Constant.NOTIFICATION_EMAIL_FROM);

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }

    }
}
