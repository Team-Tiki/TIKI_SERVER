package com.tiki.server.email.emailsender.entity;

import com.tiki.server.email.Email;
import com.tiki.server.email.emailsender.exception.EmailSenderException;
import com.tiki.server.email.emailsender.message.ErrorCode;
import com.tiki.server.email.emailverification.domain.EmailVerification;
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

import static com.tiki.server.common.constants.Constants.INIT_NUM;
import static com.tiki.server.email.emailsender.constants.Constants.*;

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
        return IntStream.range(INIT_NUM, CODE_LENGTH).mapToObj(i -> String.valueOf(random.nextInt(CODE_NUM_MAX_VALUE_PER_WORD))).collect(Collectors.joining());
    }

    private MimeMessage makeMessage(Email email, String code, String subject) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(TIKI_EMAIL);
            helper.setTo(email.getEmail());
            helper.setSubject(subject);
            helper.setText(setContext(code), true);
            helper.addInline(CERTIFICATION_PAGE_LOGO_IMAGE_VAR, new ClassPathResource(IMG_PATH));
            return message;
        } catch (Exception e) {
            throw new EmailSenderException(ErrorCode.MESSAGE_HELPER_ERROR);
        }
    }

    private String setContext(String code) {
        Context context = new Context();
        context.setVariable(CERTIFICATION_PAGE_CODEE_VAR, code);
        return templateEngine.process(TEMPLATE_NAME, context);
    }
}
