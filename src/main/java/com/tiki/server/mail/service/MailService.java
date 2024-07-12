package com.tiki.server.mail.service;

import com.tiki.server.mail.dto.request.EmailRequestDto;
import com.tiki.server.member.exception.MemberException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.naming.Context;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender mailSender;
    private final RedisUtil redisUtil;



    @Value("${spring.mail.username}")
    private String configEmail;

    // 메일 보내기
    public void sendMail(EmailRequestDto emailRequestDto) throws MessagingException {
        checkMailFormat(emailRequestDto);
//        if (redisUtil.existData(toEmail)) {
//            redisUtil.deleteData(toEmail);
//        }
//        MimeMessage emailForm = createEmailForm(toEmail);
//
//        mailSender.send(emailForm);
    }

    private void checkMailFormat(EmailRequestDto emailRequestDto) {
        val address = emailRequestDto.getEmail();
        if(!(address.contains("@") && !address.endsWith(".edu") && address.endsWith(".ac.kr") && address.endsWith("academy "))){
            throw new MemberException()
        }
    }

    private String createdCode() {
        int leftLimit = 48; // number '0'
        int rightLimit = 122; // alphabet 'z'
        int targetStringLength = 6;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <=57 || i >=65) && (i <= 90 || i>= 97))
                .limit(targetStringLength)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
    }

    private String setContext(String code) {
        Context context = new Context();
        TemplateEngine templateEngine = new TemplateEngine();
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();

        context.setVariable("code", code);


        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCacheable(false);

        templateEngine.setTemplateResolver(templateResolver);

        return templateEngine.process("mail", context);
    }


    // 메일 반환

    private MimeMessage createEmailForm(String email) throws MessagingException {

        String authCode = createdCode();

        MimeMessage message = mailSender.createMimeMessage();
        message.addRecipients(MimeMessage.RecipientType.TO, email);
        message.setSubject("안녕하세요 인증번호입니다.");
        message.setFrom(configEmail);
        message.setText(setContext(authCode), "utf-8", "html");

        redisUtil.setDataExpire(email, authCode, 60 * 30L);

        return message;
    }

    // 코드 검증
    public Boolean verifyEmailCode(String email, String code) {
        String codeFoundByEmail = redisUtil.getData(email);
        System.out.println(codeFoundByEmail);
        if (codeFoundByEmail == null) {
            return false;
        }
        return codeFoundByEmail.equals(code);
    }

    public String makeMemberId(String email) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(email.getBytes());
        md.update(LocalDateTime.now().toString().getBytes());
        return Arrays.toString(md.digest());
    }

}