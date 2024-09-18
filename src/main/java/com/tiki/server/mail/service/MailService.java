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

import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Optional;
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
		String email = mailRequest.email();
		checkSignUpEmailFormat(email);
		sendMail(email, MAIL_SUBJECT_SIGN_UP);
	}

	@Transactional
	public void sendChangingPassword(MailRequest mailRequest) {
		String email = mailRequest.email();
		checkPasswordEmailFormat(email);
		sendMail(email, MAIL_SUBJECT_CHANGING_PASSWORD);
	}

	public void checkCode(CodeCheck codeCheck) {
		String email = codeCheck.email();
		checkEmailFormat(email);
		Optional<Mail> mail = mailFinder.findById(email);
		if (mail.isEmpty()) {
			throw new MailException(INVALID_REQUEST);
		}
		if (!mail.get().getCode().equals(codeCheck.code())) {
			throw new MailException(INVALID_MATCHED);
		}
	}

	private void checkSignUpEmailFormat(String email) {
		checkMemberPresent(email);
		checkEmailFormat(email);
	}

	private void checkMemberPresent(String email) {
		memberFinder.findByEmail(email).ifPresent(member -> {
			throw new MemberException(CONFLICT_MEMBER);
		});
	}

	private void checkPasswordEmailFormat(String email) {
		checkMemberEmpty(email);
		checkEmailFormat(email);
	}

	private void checkMemberEmpty(String email) {
		if (memberFinder.findByEmail(email).isEmpty()) {
			throw new MemberException(INVALID_MEMBER);
		}
	}

	private void sendMail(String email, String subject) {
		MimeMessage mail = makeMail(email, subject);
		send(mail);
	}

	private void checkEmailFormat(String email) {
		if (!(email.endsWith(MAIL_FORMAT_EDU) || email.endsWith(MAIL_FORMAT_AC_KR))) {
			throw new MemberException(INVALID_EMAIL);
		}
	}

	private MimeMessage makeMail(String email, String subject) {
		String code = generateRandomValue();
		MimeMessage message = makeMessage(email, code, subject);
		mailSaver.save(Mail.of(email, code));
		return message;
	}

	private MimeMessage makeMessage(String email, String code, String subject) {
		MimeMessage message = javaMailSender.createMimeMessage();
		try {
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");
			helper.setFrom(TIKI_EMAIL);
			helper.setTo(email);
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
		Random random = new Random();
		return IntStream.range(0, 6).mapToObj(i -> String.valueOf(random.nextInt(10))).collect(Collectors.joining());
	}

	public String setContext(String code) {
		Context context = new Context();
		context.setVariable("code", code);
		return templateEngine.process(TEMPLATE_NAME, context);
	}
}