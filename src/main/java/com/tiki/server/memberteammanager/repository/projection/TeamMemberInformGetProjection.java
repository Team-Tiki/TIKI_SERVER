package com.tiki.server.memberteammanager.repository.projection;

import com.tiki.server.common.entity.Position;

public interface TeamMemberInformGetProjection {
    String getMemberName();
    String getMemberEmail();
    Position getMemberPosition();
}
