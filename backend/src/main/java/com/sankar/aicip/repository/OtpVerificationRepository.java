package com.sankar.aicip.repository;

import com.sankar.aicip.entity.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Long> {
    Optional<OtpVerification> findFirstByTargetOrderByCreatedAtDesc(String target);
    Optional<OtpVerification> findByTargetAndResetToken(String target, String resetToken);
    void deleteByTarget(String target);
}
