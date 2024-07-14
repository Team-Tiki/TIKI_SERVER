package com.tiki.server.mail.constants;

import org.springframework.beans.factory.annotation.Value;

public class MailConstants {

    @Value("${mail.password}")
    public static String MailPassword;
    public static final String TIKI_ADDRESS = "hello.wer.tiki@gmail.com";
    public static final String MAIL_SUBJECT_SIGN_UP = "[Ti.Ki] 회원가입: 이메일 인증번호 안내";
    public static final String MAIL_SUBJECT_CHANGING_PASSWORD = "[Ti.Ki] 회원가입: 이메일 인증번호 안내";
    public static final String MAIL_FORMAT_EDU = ".edu";
    public static final String MAIL_FORMAT_AC_KR = ".ac.kr";
}
