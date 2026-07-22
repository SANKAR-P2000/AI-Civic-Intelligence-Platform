# Complaint Repository

## Objective

Provide database access methods for Complaint entities.

---

## Repository

ComplaintRepository extends JpaRepository<Complaint, Long>

---

## Methods

### findByCitizen(User citizen)

Returns all complaints submitted by a citizen.

---

### findByStatus(ComplaintStatus status)

Returns all complaints having the specified status.

---

### findByCitizenAndStatus(User citizen, ComplaintStatus status)

Returns complaints submitted by a citizen with a specific status.

---

## Benefits

- Simplifies complaint retrieval.
- Supports citizen complaint history.
- Enables filtering by complaint status.
- Provides foundation for dashboards and analytics.

---

## Status

✅ Completed
