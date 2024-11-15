package com.tiki.server.timeblock.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentDeleter;
import com.tiki.server.document.adapter.DocumentFinder;
import com.tiki.server.document.adapter.DocumentSaver;
import com.tiki.server.document.entity.Document;
import com.tiki.server.document.vo.DocumentVO;
import com.tiki.server.memberteammanager.adapter.MemberTeamManagerFinder;
import com.tiki.server.memberteammanager.entity.MemberTeamManager;
import com.tiki.server.team.adapter.TeamFinder;
import com.tiki.server.team.entity.Team;
import com.tiki.server.timeblock.adapter.TimeBlockDeleter;
import com.tiki.server.timeblock.adapter.TimeBlockFinder;
import com.tiki.server.timeblock.adapter.TimeBlockSaver;
import com.tiki.server.timeblock.dto.request.TimeBlockCreateRequest;
import com.tiki.server.timeblock.dto.response.TimeBlockCreateResponse;
import com.tiki.server.timeblock.dto.response.TimeBlockDetailGetResponse;
import com.tiki.server.timeblock.dto.response.TimelineGetResponse;
import com.tiki.server.timeblock.entity.TimeBlock;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TimeBlockService {

    private final TeamFinder teamFinder;
    private final MemberTeamManagerFinder memberTeamManagerFinder;
    private final TimeBlockSaver timeBlockSaver;
    private final TimeBlockFinder timeBlockFinder;
    private final TimeBlockDeleter timeBlockDeleter;
    private final DocumentSaver documentSaver;
    private final DocumentFinder documentFinder;
    private final DocumentDeleter documentDeleter;

    @Transactional
    public TimeBlockCreateResponse createTimeBlock(
            long memberId,
            long teamId,
            String type,
            TimeBlockCreateRequest request
    ) {
        Team team = teamFinder.findById(teamId);
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        Position accessiblePosition = Position.getAccessiblePosition(type);
        memberTeamManager.checkMemberAccessible(accessiblePosition);
        TimeBlock timeBlock = saveTimeBlock(team, accessiblePosition, request);
        saveDocuments(request.files(), timeBlock);
        return TimeBlockCreateResponse.of(timeBlock.getId());
    }

    public TimelineGetResponse getTimeline(long memberId, long teamId, String type, String date) {
        Team team = teamFinder.findById(teamId);
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        Position accessiblePosition = Position.getAccessiblePosition(type);
        memberTeamManager.checkMemberAccessible(accessiblePosition);
        List<TimeBlock> timeBlocks = timeBlockFinder.findByTeamAndAccessiblePositionAndDate(
                team.getId(), accessiblePosition.name(), date);
        return TimelineGetResponse.from(timeBlocks);
    }

    public TimeBlockDetailGetResponse getTimeBlockDetail(long memberId, long teamId, long timeBlockId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdOrElseThrow(timeBlockId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        List<DocumentVO> documents = documentFinder.findAllByTimeBlockId(timeBlockId);
        return TimeBlockDetailGetResponse.from(documents);
    }

    @Transactional
    public void deleteTimeBlock(long memberId, long teamId, long timeBlockId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamIdOrElseThrow(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdOrElseThrow(timeBlockId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        documentDeleter.deleteAllByTimeBlockId(timeBlock.getId());
        timeBlockDeleter.deleteById(timeBlock.getId());
    }

    private TimeBlock saveTimeBlock(Team team, Position accessiblePosition, TimeBlockCreateRequest request) {
        return timeBlockSaver.save(TimeBlock.of(team, accessiblePosition, request));
    }

    private void saveDocuments(Map<String, String> files, TimeBlock timeBlock) {
        files.forEach((fileName, fileUrl) -> documentSaver.save(Document.of(fileName, fileUrl, timeBlock)));
    }
}
