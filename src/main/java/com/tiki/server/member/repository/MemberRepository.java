package com.tiki.server.member.repository;

import com.tiki.server.common.entity.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import com.tiki.server.member.entity.Member;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByEmail(final Email email);
}
