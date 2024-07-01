package com.tiki.server.memberteammanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.memberteammanager.entity.MemberTeamManager;

public interface MemberTeamManagerRepository extends JpaRepository<MemberTeamManager, Long> {
}
