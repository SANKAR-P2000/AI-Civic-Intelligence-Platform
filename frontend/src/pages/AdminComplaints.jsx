import { useEffect, useState } from "react";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import Pill from "../components/ui/Pill.jsx";
import { Spinner } from "../components/ui/Spinner.jsx";
import complaintService from "../services/complaints.js";
import "./AdminComplaints.css";

const STATUS_OPTIONS = [
  "PENDING",
  "UNDER_REVIEW",
  "IN_PROGRESS",
  "RESOLVED",
  "REJECTED",
];

function formatDate(value) {
  if (!value) return "—";
  const date = new Date(value);
  if (Number.isNaN(date.getTime())) return "—";
  return date.toLocaleDateString(undefined, {
    year: "numeric",
    month: "short",
    day: "numeric",
  });
}

function formatCategory(value = "") {
  return value.toLowerCase().replace(/_/g, " ");
}

function AdminComplaints() {
  const [complaints, setComplaints] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [keyword, setKeyword] = useState("");
  const [statusFilter, setStatusFilter] = useState("");
  const [updatingId, setUpdatingId] = useState(null);

  useEffect(() => {
    let active = true;
    (async () => {
      try {
        const data = await complaintService.adminGetAllComplaints();
        if (active) setComplaints(data);
      } catch (err) {
        if (active) setError(err.message || "Failed to load complaints.");
      } finally {
        if (active) setLoading(false);
      }
    })();
    return () => {
      active = false;
    };
  }, []);

  const handleSearch = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");
    try {
      const data = keyword.trim()
        ? await complaintService.adminSearch(keyword.trim())
        : await complaintService.adminGetAllComplaints();
      setComplaints(data);
    } catch (err) {
      setError(err.message || "Search failed.");
    } finally {
      setLoading(false);
    }
  };

  const handleStatusFilter = async (nextStatus) => {
    setStatusFilter(nextStatus);
    setLoading(true);
    setError("");
    try {
      const data = nextStatus
        ? await complaintService.adminGetByStatus(nextStatus)
        : await complaintService.adminGetAllComplaints();
      setComplaints(data);
    } catch (err) {
      setError(err.message || "Filter failed.");
    } finally {
      setLoading(false);
    }
  };

  const handleUpdateStatus = async (id, newStatus) => {
    setUpdatingId(id);
    setError("");
    try {
      await complaintService.adminUpdateStatus(id, newStatus);
      setComplaints((prev) =>
        prev.map((c) => (c.id === id ? { ...c, status: newStatus } : c)),
      );
    } catch (err) {
      setError(err.message || "Failed to update complaint status.");
    } finally {
      setUpdatingId(null);
    }
  };

  return (
    <div className="admin-complaints">
      <SectionHeading
        eyebrow="Admin Panel"
        title="Manage Complaints"
        subtitle="View, search, filter, and update the status of all civic complaints."
      />

      <GlassCard className="admin-complaints__toolbar">
        <form className="admin-complaints__search" onSubmit={handleSearch}>
          <Input
            name="keyword"
            label="Search"
            placeholder="Search by title, location, citizen, or email"
            value={keyword}
            onChange={(e) => setKeyword(e.target.value)}
          />
          <Button type="submit" className="admin-complaints__search-btn">
            Search
          </Button>
        </form>

        <div className="admin-complaints__filters">
          <span className="admin-complaints__filter-label">Status:</span>
          <button
            type="button"
            className={`admin-complaints__filter ${
              statusFilter === "" ? "admin-complaints__filter--active" : ""
            }`}
            onClick={() => handleStatusFilter("")}
          >
            All
          </button>
          {STATUS_OPTIONS.map((s) => (
            <button
              key={s}
              type="button"
              className={`admin-complaints__filter ${
                statusFilter === s ? "admin-complaints__filter--active" : ""
              }`}
              onClick={() => handleStatusFilter(s)}
            >
              {formatCategory(s)}
            </button>
          ))}
        </div>
      </GlassCard>

      {error && <p className="admin-complaints__error">{error}</p>}

      {loading ? (
        <div className="admin-complaints__loading">
          <Spinner size="lg" />
          <p>Loading complaints...</p>
        </div>
      ) : complaints.length === 0 ? (
        <GlassCard className="admin-complaints__empty">
          <p>No complaints found.</p>
        </GlassCard>
      ) : (
        <div className="admin-complaints__list">
          {complaints.map((c) => (
            <GlassCard key={c.id} className="admin-complaints__item">
              <div className="admin-complaints__item-head">
                <div className="admin-complaints__item-title">
                  <span className="admin-complaints__id">#{c.id}</span>
                  <h3>{c.title}</h3>
                </div>
                <Pill>{c.status}</Pill>
              </div>

              <p className="admin-complaints__desc">{c.description}</p>

              <div className="admin-complaints__meta">
                <span>🗂️ {formatCategory(c.category)}</span>
                {c.location && <span>📍 {c.location}</span>}
                {c.citizenName && <span>👤 {c.citizenName}</span>}
                {c.citizenEmail && <span>✉️ {c.citizenEmail}</span>}
                <span>🕒 {formatDate(c.createdAt)}</span>
              </div>

              {c.imageUrl && (
                <img
                  className="admin-complaints__image"
                  src={c.imageUrl}
                  alt={c.title}
                  loading="lazy"
                />
              )}

              <div className="admin-complaints__actions">
                <span className="admin-complaints__actions-label">
                  Update status:
                </span>
                <select
                  className="aicip-field__input admin-complaints__status-select"
                  value={c.status}
                  disabled={updatingId === c.id}
                  onChange={(e) => handleUpdateStatus(c.id, e.target.value)}
                >
                  {STATUS_OPTIONS.map((s) => (
                    <option key={s} value={s}>
                      {formatCategory(s)}
                    </option>
                  ))}
                </select>
              </div>
            </GlassCard>
          ))}
        </div>
      )}
    </div>
  );
}

export default AdminComplaints;
