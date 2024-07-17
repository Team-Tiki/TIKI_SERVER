package com.tiki.server.mail.repository;

import com.tiki.server.mail.entity.Mail;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends CrudRepository<Mail, Long> {

    Optional<Mail> findById(String email);
}
