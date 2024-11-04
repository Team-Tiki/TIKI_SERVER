package com.tiki.server.note.entity.vo;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class AnswerHowToFixVO {

    private String answer;

    public static AnswerHowToFixVO from(final String answer) {
        return new AnswerHowToFixVO(answer);
    }
}
