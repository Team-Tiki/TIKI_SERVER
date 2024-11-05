package com.tiki.server.note.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class NoteFree extends Note {

    private String contents;

    // 정적 팩토리 메서드
    public static NoteFree of(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            long teamId
    ) {
        return new NoteFree(title, complete, startDate, endDate, contents, teamId);
    }

    // 생성자
    private NoteFree(
            String title,
            boolean complete,
            LocalDate startDate,
            LocalDate endDate,
            String contents,
            long teamId
    ) {
        super(title, complete, startDate, endDate, teamId);
        this.contents = contents;
    }
}
