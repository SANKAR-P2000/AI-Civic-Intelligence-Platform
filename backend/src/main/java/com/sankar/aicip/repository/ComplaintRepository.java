package com.sankar.aicip.repository;

import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import com.sankar.aicip.dto.response.CategoryStatisticsResponse;
import com.sankar.aicip.dto.response.StatusStatisticsResponse;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long> {

    List<Complaint> findByCitizen(User citizen);
    long count();

    long countByStatus(ComplaintStatus status);

    long countByCategory(ComplaintCategory category);

    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findByCitizenAndStatus(User citizen,
                                           ComplaintStatus status);
    @Query("""
SELECT new com.sankar.aicip.dto.response.CategoryStatisticsResponse(
c.category,
COUNT(c)
)
FROM Complaint c
GROUP BY c.category
ORDER BY COUNT(c) DESC
""")
    List<CategoryStatisticsResponse> getComplaintCountByCategory();


    @Query("""
SELECT new com.sankar.aicip.dto.response.StatusStatisticsResponse(
c.status,
COUNT(c)
)
FROM Complaint c
GROUP BY c.status
ORDER BY COUNT(c) DESC
""")
    List<StatusStatisticsResponse> getComplaintCountByStatus();
}