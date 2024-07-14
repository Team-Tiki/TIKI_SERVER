package com.tiki.server.mail.service;

import com.tiki.server.mail.adapter.MailFinder;
import com.tiki.server.mail.adapter.MailSaver;
import com.tiki.server.mail.dto.request.CodeCheck;
import com.tiki.server.mail.dto.request.MailRequest;
import com.tiki.server.mail.entity.Mail;
import com.tiki.server.mail.exception.MailException;
import com.tiki.server.member.adapter.MemberFinder;
import com.tiki.server.member.exception.MemberException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.tiki.server.mail.message.ErrorCode.*;
import static com.tiki.server.member.message.ErrorCode.*;
import static com.tiki.server.mail.constants.MailConstants.*;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MailService {

    private final SpringTemplateEngine templateEngine;
    private final JavaMailSender javaMailSender;
    private final MemberFinder memberFinder;
    private final MailFinder mailFinder;
    private final MailSaver mailSaver;

    @Transactional
    public void sendSignUp(MailRequest mailRequest) {
        val address = mailRequest.address();
        checkSignUpEmailFormat(address);
        sendMail(address, MAIL_SUBJECT_SIGN_UP);
    }

    @Transactional
    public void sendChangingPassword(MailRequest mailRequest) {
        val address = mailRequest.address();
        checkPasswordEmailFormat(address);
        sendMail(address, MAIL_SUBJECT_CHANGING_PASSWORD);
    }

    public void checkCode(CodeCheck codeCheck) {
        val address = codeCheck.address();
        checkEmailFormat(address);
        val mail = mailFinder.findById(address);
        if (mail.isEmpty()) {
            throw new MailException(INVALID_REQUEST);
        }
        if (!mail.get().getCode().equals(codeCheck.code())) {
            throw new MailException(INVALID_MATCHED);
        }
    }

    private void checkSignUpEmailFormat(String address) {
        checkMemberPresent(address);
        checkEmailFormat(address);
    }

    private void checkMemberPresent(String address) {
        memberFinder.findByEmail(address).ifPresent(member -> {
            throw new MemberException(CONFLICT_MEMBER);
        });
    }

    private void checkPasswordEmailFormat(String address) {
        checkMemberEmpty(address);
        checkEmailFormat(address);
    }

    private void checkMemberEmpty(String address) {
        if (memberFinder.findByEmail(address).isEmpty()) {
            throw new MemberException(INVALID_MEMBER);
        }
    }

    private void sendMail(String address, String subject) {
        val mail = makeMail(address, subject);
        send(mail);
    }

    private void checkEmailFormat(String address) {
        if (!(address.endsWith(MAIL_FORMAT_EDU) || address.endsWith(MAIL_FORMAT_AC_KR))) {
            throw new MemberException(INVALID_EMAIL);
        }
    }

    private MimeMessage makeMail(String address, String subject) {
        val code = generateRandomValue();
        val message = makeMessage(address, code, subject);
        mailSaver.save(Mail.of(address, code));
        return message;
    }

    private MimeMessage makeMessage(String address, String code, String subject) {
        val message = javaMailSender.createMimeMessage();
        try {
            val helper = new MimeMessageHelper(message, true, "utf-8");
            helper.setFrom(TIKI_ADDRESS);
            helper.setTo(address);
            helper.setSubject(subject);
            helper.setText(setContext(code), true);
            helper.addInline("image", new ClassPathResource(IMG_PATH));
            return message;
        } catch (Exception e) {
            throw new MailException(MESSAGE_HELPER_ERROR);
        }
    }

    private void send(MimeMessage email) {
        javaMailSender.send(email);
    }

    private static String generateRandomValue() {
        val random = new Random();

        return IntStream.range(0, 6).mapToObj(i -> String.valueOf(random.nextInt(10))).collect(Collectors.joining());
    }

    public String setContext(String code) {
        val context = new Context();
        context.setVariable("code", code);
        return templateEngine.process(TEMPLATE_NAME, context);
    }
}