package com.tiki.server.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tiki.server.member.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
