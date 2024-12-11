package com.tiki.server.team.entity;

import static com.tiki.server.common.Constants.INIT_NUM;
import static com.tiki.server.team.entity.Subscribe.BASIC;
import static com.tiki.server.team.message.ErrorCode.EXCEED_TEAM_CAPACITY;
import static com.tiki.server.team.message.ErrorCode.TOO_SHORT_PERIOD;
import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.GenerationType.IDENTITY;
import static lombok.AccessLevel.PRIVATE;
import static lombok.AccessLevel.PROTECTED;

import com.tiki.server.common.entity.BaseTime;
import com.tiki.server.common.entity.University;
import com.tiki.server.team.dto.request.TeamCreateRequest;

import com.tiki.server.team.exception.TeamException;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder(access = PRIVATE)
@AllArgsConstructor(access = PRIVATE)
@NoArgsConstructor(access = PROTECTED)
public class Team extends BaseTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "team_id")
    private Long id;

    private String name;

    private String overview;

    @Enumerated(value = STRING)
    private Category category;

    @Enumerated(value = STRING)
    private University univ;

    @Enumerated(value = STRING)
    private Subscribe subscribe;

    private double usage;

    private String imageUrl;

    private String iconImageUrl;

    private LocalDate namingUpdatedAt;

    public static Team of(TeamCreateRequest request, University univ) {
        return Team.builder()
                .name(request.name())
                .category(request.category())
                .univ(univ)
                .subscribe(BASIC)
                .usage(INIT_NUM)
                .namingUpdatedAt(null)
                .iconImageUrl(request.iconImageUrl())
                .build();
    }

    public void updateName(final String name) {
        if (!canChangeName()) {
            throw new TeamException(TOO_SHORT_PERIOD);
        }
        this.name = name;
        this.namingUpdatedAt = LocalDate.now();
    }

    public void updateIconImageUrl(final String url) {
        this.iconImageUrl = url;
    }

    public boolean isDefaultImage() {
        return this.getIconImageUrl().isBlank();
    }

    public void addUsage(double capacity) {
        if (usage + capacity > subscribe.getCapacity()) {
            throw new TeamException(EXCEED_TEAM_CAPACITY);
        }
        usage += capacity;
    }

    public void restoreUsage(double capacity) {
        usage -= capacity;
    }

    private boolean canChangeName() {
        if (namingUpdatedAt == null) {
            return true;
        }
        long daysBetween = ChronoUnit.DAYS.between(namingUpdatedAt, LocalDate.now());
        return daysBetween >= 30;
    }
}
