package com.sankar.aicip.repository;

import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCitizen(User citizen);

    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findByCitizenAndStatus(User citizen,
                                           ComplaintStatus status);
}