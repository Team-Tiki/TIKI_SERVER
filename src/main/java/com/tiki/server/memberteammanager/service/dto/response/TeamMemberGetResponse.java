package com.tiki.server.memberteammanager.service.dto.response;

import com.tiki.server.common.entity.Email;
import com.tiki.server.memberteammanager.repository.projection.NameAndEmailProjection;
import org.yaml.snakeyaml.emitter.Emitable;

public record TeamMemberGetResponse (
        String name,
        String email
){
    public static TeamMemberGetResponse from(NameAndEmailProjection projection){
        return new TeamMemberGetResponse(projection.getName(), projection.getEmail());
    }
}
