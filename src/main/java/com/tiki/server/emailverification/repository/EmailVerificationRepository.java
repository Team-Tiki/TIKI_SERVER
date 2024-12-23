package com.tiki.server.emailverification.repository;

import com.tiki.server.emailverification.domain.EmailVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends CrudRepository<EmailVerification, Long> {

    Optional<EmailVerification> findById(final String email);
}
