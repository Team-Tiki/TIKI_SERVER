package com.tiki.server.email;

import com.tiki.server.member.exception.MemberException;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;
import org.apache.commons.validator.routines.EmailValidator;

import static com.tiki.server.email.Constants.MAIL_FORMAT_AC_KR;
import static com.tiki.server.email.Constants.MAIL_FORMAT_EDU;
import static com.tiki.server.member.message.ErrorCode.INVALID_EMAIL;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Email {

    @Column(nullable = false)
    private String email;

    public static Email from(final String email){
        checkMailFormat(email);
        return new Email(email);
    }

    private static void checkMailFormat(final String email) {
        if (!EmailValidator.getInstance().isValid(email) || !(email.endsWith(MAIL_FORMAT_EDU) ||
			email.endsWith(MAIL_FORMAT_AC_KR))) {
            throw new MemberException(INVALID_EMAIL);
        }
    }
}
