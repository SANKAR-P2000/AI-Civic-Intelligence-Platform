package com.sankar.aicip.repository.specification;

import com.sankar.aicip.entity.Complaint;
import com.sankar.aicip.enums.ComplaintCategory;
import com.sankar.aicip.enums.ComplaintStatus;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ComplaintSpecification {

    public static Specification<Complaint> filterComplaints(
            String keyword,
            ComplaintCategory category,
            ComplaintStatus status,
            String city,
            LocalDate fromDate,
            LocalDate toDate,
            Long citizenId
    ) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.trim().isEmpty()) {
                String pattern = "%" + keyword.trim().toLowerCase() + "%";
                Predicate titleLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("title")), pattern);
                Predicate descLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern);
                Predicate locLike = criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), pattern);

                // Check if keyword is numeric ID
                try {
                    Long idVal = Long.parseLong(keyword.trim());
                    Predicate idMatch = criteriaBuilder.equal(root.get("id"), idVal);
                    predicates.add(criteriaBuilder.or(titleLike, descLike, locLike, idMatch));
                } catch (NumberFormatException e) {
                    predicates.add(criteriaBuilder.or(titleLike, descLike, locLike));
                }
            }

            if (category != null) {
                predicates.add(criteriaBuilder.equal(root.get("category"), category));
            }

            if (status != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), status));
            }

            if (city != null && !city.trim().isEmpty()) {
                String cityPattern = "%" + city.trim().toLowerCase() + "%";
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("location")), cityPattern));
            }

            if (fromDate != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("createdAt"), fromDate.atStartOfDay()));
            }

            if (toDate != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("createdAt"), toDate.atTime(LocalTime.MAX)));
            }

            if (citizenId != null) {
                predicates.add(criteriaBuilder.equal(root.get("citizen").get("id"), citizenId));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
