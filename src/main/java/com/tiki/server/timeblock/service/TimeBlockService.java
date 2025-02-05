package com.tiki.server.timeblock.service;

import com.tiki.server.notetimeblockmanager.adapter.NTBDeleter;
import com.tiki.server.timeblock.dto.request.TimeBlockUpdateRequest;
import com.tiki.server.timeblock.service.dto.response.AllTimeBlockServiceResponse;
import java.util.List;

import com.tiki.server.document.entity.Document;
import com.tiki.server.documenttimeblockmanager.adapter.DTBAdapter;
import com.tiki.server.documenttimeblockmanager.entity.DTBManager;
import com.tiki.server.note.adapter.NoteFinder;
import com.tiki.server.note.entity.Note;
import com.tiki.server.notetimeblockmanager.adapter.NTBFinder;
import com.tiki.server.notetimeblockmanager.entity.NTBManager;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tiki.server.common.entity.Position;
import com.tiki.server.document.adapter.DocumentFinder;
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
import com.tiki.server.timeblock.service.dto.DocumentTagInfo;

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
    private final DocumentFinder documentFinder;
    private final DTBAdapter dtbAdapter;
    private final NTBFinder ntbFinder;
    private final NoteFinder noteFinder;
    private final NTBDeleter ntbDeleter;

    @Transactional
    public TimeBlockCreateResponse createTimeBlock(
            final long memberId,
            final long teamId,
            final String type,
            final TimeBlockCreateRequest request
    ) {
        Team team = teamFinder.findById(teamId);
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        Position accessiblePosition = Position.getAccessiblePosition(type);
        memberTeamManager.checkMemberAccessible(accessiblePosition);
        validateDocuments(team, request.documentIds());
        TimeBlock timeBlock = saveTimeBlock(team, accessiblePosition, request);
        dtbAdapter.saveAll(timeBlock, request.documentIds());
        return TimeBlockCreateResponse.of(timeBlock.getId());
    }

    public TimelineGetResponse getTimeline(
            final long memberId,
            final long teamId,
            final String type,
            final String date
    ) {
        Team team = teamFinder.findById(teamId);
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        Position accessiblePosition = Position.getAccessiblePosition(type);
        memberTeamManager.checkMemberAccessible(accessiblePosition);
        List<TimeBlock> timeBlocks = timeBlockFinder.findByTeamAndAccessiblePositionAndDate(
                team.getId(), accessiblePosition.name(), date);
        return TimelineGetResponse.from(timeBlocks);
    }

    public AllTimeBlockServiceResponse getAllTimeBlock(
            final long memberId,
            final long teamId
    ) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        List<TimeBlock> timeBlocks = timeBlockFinder.findAllByTeamId(teamId);
        return AllTimeBlockServiceResponse.from(timeBlocks);
    }

    public TimeBlockDetailGetResponse getTimeBlockDetail(
            final long memberId,
            final long teamId,
            final long timeBlockId
    ) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdAndTeamId(timeBlockId, teamId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        List<DocumentTagInfo> documents = getDocumentsInfo(timeBlock);
        List<Note> notes = getNotes(timeBlock.getId());
        return TimeBlockDetailGetResponse.from(documents, notes);
    }

    @Transactional
    public void updateTimeBlock(
        final long memberId,
        final long teamId,
        final long timeBlockId,
        final TimeBlockUpdateRequest request
    ) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdAndTeamId(timeBlockId, teamId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        timeBlock.updateNameAndDate(request);
    }

    @Transactional
    public void deleteTimeBlock(final long memberId, final long teamId, final long timeBlockId) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdAndTeamId(timeBlockId, teamId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        dtbAdapter.deleteAllByTimeBlock(timeBlock);
        ntbDeleter.deleteAllByTimeBlock(timeBlock);
        timeBlockDeleter.deleteById(timeBlock.getId());
    }

    @Transactional
    public void createDocumentTag(
            final long memberId,
            final long teamId,
            final long timeBlockId,
            final List<Long> documentIds
    ) {
        Team team = teamFinder.findById(teamId);
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdAndTeamId(timeBlockId, teamId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        validateDocuments(team, documentIds);
        dtbAdapter.saveAll(timeBlock, documentIds);
    }

    @Transactional
    public void deleteDocumentTag(
            final long memberId,
            final long teamId,
            final long timeBlockId,
            final List<Long> tagIds
    ) {
        MemberTeamManager memberTeamManager = memberTeamManagerFinder.findByMemberIdAndTeamId(memberId, teamId);
        TimeBlock timeBlock = timeBlockFinder.findByIdAndTeamId(timeBlockId, teamId);
        memberTeamManager.checkMemberAccessible(timeBlock.getAccessiblePosition());
        List<DTBManager> dtbManagers = dtbAdapter.getAllByIds(tagIds);
        dtbManagers.forEach(dtbManager -> dtbManager.validateTimeBlock(timeBlock));
        dtbAdapter.deleteAll(dtbManagers);
    }

    private void validateDocuments(final Team team, final List<Long> documentIds) {
        documentFinder.findAllByIdAndTeamId(documentIds, team.getId());
    }

    private TimeBlock saveTimeBlock(
            final Team team,
            final Position accessiblePosition,
            final TimeBlockCreateRequest request
    ) {
        return timeBlockSaver.save(TimeBlock.of(team, accessiblePosition, request));
    }

    private List<DocumentTagInfo> getDocumentsInfo(final TimeBlock timeBlock) {
        List<DTBManager> dtbManagers = dtbAdapter.getAllByTimeBlock(timeBlock);
        return dtbManagers.stream()
                .map(this::getDocumentTagInfo)
                .toList();
    }

    private DocumentTagInfo getDocumentTagInfo(final DTBManager dtbManager) {
        Document document = documentFinder.findById(dtbManager.getDocumentId());
        return DocumentTagInfo.of(document, dtbManager);
    }

    private List<Note> getNotes(final long timeBlockId) {
        List<NTBManager> noteTimeBlockManagers = ntbFinder.findAllByTimeBlockId(timeBlockId);
        return noteTimeBlockManagers.stream()
                .map(noteTimeBlockManager -> noteFinder.findById(noteTimeBlockManager.getNoteId()))
                .toList();
    }
}
