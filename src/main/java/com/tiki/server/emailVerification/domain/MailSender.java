package com.tiki.server.emailVerification.domain;

import com.tiki.server.common.entity.Email;
import com.tiki.server.emailVerification.exception.EmailVerificationException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tiki.server.emailVerification.constants.EmailConstants.*;
import static com.tiki.server.emailVerification.message.ErrorCode.MESSAGE_HELPER_ERROR;

@Component
@RequiredArgsConstructor
public class MailSender {

    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public EmailVerification sendVerificationMail(Email email, String subject) {
        String code = generateRandomValue();
        MimeMessage message = makeMessage(email, code, subject);
        javaMailSender.send(message);
        return EmailVerification.of(email, code);
    }

    private static String generateRandomValue() {
        Random random = new Random();
        return IntStream.range(0, 6).mapToObj(i -> String.valueOf(random.nextInt(10))).collect(Collectors.joining());
    }

    private MimeMessage makeMessage(Email email, String code, String subject) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(TIKI_EMAIL);
            helper.setTo(email.getEmail());
            helper.setSubject(subject);
            helper.setText(setContext(code), true);
            helper.addInline("image", new ClassPathResource(IMG_PATH));
            return message;
        } catch (Exception e) {
            throw new EmailVerificationException(MESSAGE_HELPER_ERROR);
        }
    }

    private String setContext(String code) {
        Context context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(TEMPLATE_NAME, context);
    }
}
