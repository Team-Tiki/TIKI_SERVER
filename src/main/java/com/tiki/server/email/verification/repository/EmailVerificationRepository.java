package com.tiki.server.email.verification.repository;

import com.tiki.server.email.verification.domain.EmailVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmailVerificationRepository extends CrudRepository<EmailVerification, Long> {

    Optional<EmailVerification> findById(String email);
}
