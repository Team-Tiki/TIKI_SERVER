package com.tiki.server.email.emailsender.constants;

public class Constants {

    /* 공통 상수 */
    public static final String TIKI_EMAIL = "hello.wer.tiki@gmail.com";
    public static final String IMG_PATH = "images/mail_logo.png";

    /* 이메일 인증 관련 상수 */
    public static final String MAIL_SUBJECT_SIGN_UP = "[Ti.Ki] 회원가입: 이메일 인증번호 안내";
    public static final String MAIL_SUBJECT_CHANGING_PASSWORD = "[Ti.Ki] 회원가입: 이메일 인증번호 안내";
    public static final String TEMPLATE_NAME = "certification";
    public static final int CODE_LENGTH = 6;
    public static final int CODE_NUM_MAX_VALUE_PER_WORD = 10;
    public static final String CERTIFICATION_PAGE_LOGO_IMAGE_VAR = "image";
    public static final String CERTIFICATION_PAGE_CODEE_VAR = "code";

    /* 이메일 초대 관련 상수 */
}
