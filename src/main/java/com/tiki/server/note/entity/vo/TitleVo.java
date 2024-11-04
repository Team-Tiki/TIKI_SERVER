package com.tiki.server.note.entity.vo;

import com.tiki.server.note.exception.NoteException;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static com.tiki.server.note.message.ErrorCode.TITLE_IS_EMPTY;
import static com.tiki.server.note.message.ErrorCode.TITLE_LENGTH_OVER;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

@Getter
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
@Embeddable
public class TitleVo {

    private String title;


    public static TitleVo from(final String title) {
        validTitleLength(title);
        return new TitleVo(title);
    }

    public static void validTitleLength(final String title) {
        if (title.isEmpty()) {
            throw new NoteException(TITLE_IS_EMPTY);
        }
        if (title.length() > 100) {
            throw new NoteException(TITLE_LENGTH_OVER);
        }
    }
}
