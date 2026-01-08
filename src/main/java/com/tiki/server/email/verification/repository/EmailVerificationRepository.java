package com.tiki.server.email.verification.repository;

import com.tiki.server.common.support.RedisRepository;
import com.tiki.server.email.verification.domain.EmailVerification;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

@RedisRepository
public interface EmailVerificationRepository extends CrudRepository<EmailVerification, Long> {

    Optional<EmailVerification> findById(final String email);
}
