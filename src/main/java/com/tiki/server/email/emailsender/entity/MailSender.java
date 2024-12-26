package com.tiki.server.email.emailsender.entity;

import com.tiki.server.email.emailsender.exception.EmailSenderException;
import com.tiki.server.email.emailsender.message.ErrorCode;
import jakarta.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import static com.tiki.server.email.emailsender.constants.Constants.*;

@Component
@RequiredArgsConstructor
public class MailSender {

    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;

    public void sendVerificationMail(final String email, final String code) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        MimeMessage message = makeMessage(email, MAIL_VERIFICATION_CODE, VERIFICATION_TEMPLATE_NAME, map);
        javaMailSender.send(message);
    }

    public void sendPasswordChangingMail(final String email, final String code, final String name) {
        Map<String, String> map = new HashMap<>();
        map.put("code", code);
        map.put("name", name);
        MimeMessage message = makeMessage(email, MAIL_PASSWORD_CHANGING_CODE, CHANGE_PASSWORD_TEMPLATE_NAME, map);
        javaMailSender.send(message);
    }

    public void sendTeamInvitationMail(final String email, final long teamId) {

    }

    private MimeMessage makeMessage(
            String email,
            String subject,
            String templateName,
            Map<String, String> map
    ) {
        MimeMessage message = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(TIKI_EMAIL);
            helper.setTo(email);
            helper.setSubject(subject);
            helper.setText(setContext(map, templateName), true);
            helper.addInline(PAGE_LOGO_IMAGE_VAR, new ClassPathResource(IMG_PATH));
            return message;
        } catch (Exception e) {
            throw new EmailSenderException(ErrorCode.MESSAGE_HELPER_ERROR);
        }
    }

    private String setContext(Map<String, String> map, String templateName) {
        Context context = new Context();
        map.forEach(context::setVariable);
        return templateEngine.process(templateName, context);
    }
}
