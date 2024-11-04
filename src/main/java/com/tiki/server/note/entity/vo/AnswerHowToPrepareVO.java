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
public class AnswerHowToPrepareVO {

    private String answer;

    public static AnswerHowToPrepareVO from(final String answer){
        return new AnswerHowToPrepareVO(answer);
    }
}
