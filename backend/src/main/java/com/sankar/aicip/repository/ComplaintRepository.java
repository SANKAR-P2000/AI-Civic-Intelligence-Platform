package com.sankar.aicip.repository;

import com.sankar.aicip.dto.response.CategoryStatisticsResponse;
import com.sankar.aicip.dto.response.StatusStatisticsResponse;
import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.entity.User;
import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ComplaintRepository extends JpaRepository<Complaint, Long>, JpaSpecificationExecutor<Complaint> {

    long count();

    long countByStatus(ComplaintStatus status);

    List<Complaint> findByCitizen(User citizen);

    long countByCategory(ComplaintCategory category);

    long countByCitizen(User citizen);

    long countByCitizenAndStatus(User citizen,
                                 ComplaintStatus status);

    List<Complaint> findByStatus(ComplaintStatus status);

    List<Complaint> findByCitizenAndStatus(User citizen,
                                           ComplaintStatus status);

    List<Complaint> findAllByOrderByCreatedAtDesc();

    List<Complaint> findByStatusOrderByCreatedAtDesc(
            ComplaintStatus status);

    List<Complaint> findByTitleContainingIgnoreCaseOrderByCreatedAtDesc(
            String keyword);

    List<Complaint> findByLocationContainingIgnoreCaseOrderByCreatedAtDesc(
            String keyword);

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

    @Query("""
            SELECT c
            FROM Complaint c
            WHERE
            LOWER(c.title) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.location) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.citizen.fullName) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.citizen.email) LIKE LOWER(CONCAT('%', :keyword, '%'))
            ORDER BY c.createdAt DESC
            """)
    List<Complaint> searchComplaints(
            @Param("keyword") String keyword);

    @Query("""
            SELECT c.category, COUNT(c)
            FROM Complaint c
            GROUP BY c.category
            ORDER BY COUNT(c) DESC
            """)
    List<Object[]> getCategoryAnalytics();

    @Query("""
            SELECT c.status, COUNT(c)
            FROM Complaint c
            GROUP BY c.status
            ORDER BY COUNT(c) DESC
            """)
    List<Object[]> getStatusAnalytics();

    @Query("""
            SELECT c.location, COUNT(c)
            FROM Complaint c
            GROUP BY c.location
            ORDER BY COUNT(c) DESC
            """)
    List<Object[]> getLocationAnalytics();

    @Query("""
            SELECT FUNCTION('DATE', c.createdAt), COUNT(c)
            FROM Complaint c
            GROUP BY FUNCTION('DATE', c.createdAt)
            ORDER BY FUNCTION('DATE', c.createdAt) DESC
            """)
    List<Object[]> getDateAnalytics();

    @Query(value = """
            SELECT c.*, ( 6371 * acos( cos( radians(:lat) ) * cos( radians( c.latitude ) )
            * cos( radians( c.longitude ) - radians(:lng) ) + sin( radians(:lat) )
            * sin( radians( c.latitude ) ) ) ) AS distance
            FROM complaints c
            WHERE c.latitude IS NOT NULL AND c.longitude IS NOT NULL
            HAVING distance <= :radiusKm
            ORDER BY distance ASC
            """,
            countQuery = """
            SELECT count(*) FROM (
                SELECT c.id, ( 6371 * acos( cos( radians(:lat) ) * cos( radians( c.latitude ) )
                * cos( radians( c.longitude ) - radians(:lng) ) + sin( radians(:lat) )
                * sin( radians( c.latitude ) ) ) ) AS distance
                FROM complaints c
                WHERE c.latitude IS NOT NULL AND c.longitude IS NOT NULL
                HAVING distance <= :radiusKm
            ) as nearby
            """,
            nativeQuery = true)
    Page<Complaint> findNearbyComplaints(
            @Param("lat") double lat,
            @Param("lng") double lng,
            @Param("radiusKm") double radiusKm,
            Pageable pageable);
}
