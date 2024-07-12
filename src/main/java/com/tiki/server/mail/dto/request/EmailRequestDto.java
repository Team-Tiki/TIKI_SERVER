package com.tiki.server.mail.dto.request;

import lombok.Getter;
import lombok.NonNull;

@Getter
public record EmailRequestDto (
        @NonNull  String email
){
}