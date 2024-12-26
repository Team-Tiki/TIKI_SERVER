package com.tiki.server.email.emailverification.domain;

import static com.tiki.server.common.constants.Constants.INIT_NUM;
import static com.tiki.server.email.emailverification.constants.Constants.CODE_LENGTH;
import static com.tiki.server.email.emailverification.constants.Constants.CODE_NUM_MAX_VALUE_PER_WORD;

import com.tiki.server.email.emailverification.exception.EmailVerificationException;
import com.tiki.server.email.emailverification.message.ErrorCode;
import jakarta.persistence.Embeddable;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class VerificationCode {

    String code;

    public static VerificationCode from() {
        return new VerificationCode(generateRandomValue());
    }

    protected void verify(String code) {
        if (!this.code.equals(code)) {
            throw new EmailVerificationException(ErrorCode.INVALID_MATCHED);
        }
    }

    private static String generateRandomValue() {
        Random random = new Random();
        return IntStream.range(INIT_NUM, CODE_LENGTH)
                .mapToObj(i -> String.valueOf(random.nextInt(CODE_NUM_MAX_VALUE_PER_WORD))).collect(
                        Collectors.joining());
    }
}
