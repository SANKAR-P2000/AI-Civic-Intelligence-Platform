import { useCallback, useEffect, useState } from "react";
import { Link } from "react-router";
import StatCard from "../components/ui/StatCard.jsx";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import ComplaintCard from "../components/ui/ComplaintCard.jsx";
import { Spinner } from "../components/ui/Spinner.jsx";
import { useAuth } from "../hooks/useAuth.js";
import complaintService from "../services/complaints.js";
import http from "../services/api.js";
import "./Dashboard.css";

function Dashboard() {
  const { user, isAdmin, isCitizen } = useAuth();
  const [stats, setStats] = useState(null);
  const [myComplaints, setMyComplaints] = useState([]);
  const [allComplaints, setAllComplaints] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadData = useCallback(async () => {
    setLoading(true);
    try {
      if (isCitizen) {
        const [complaints, statsRes] = await Promise.allSettled([
          complaintService.getMyComplaints(),
          complaintService.getMyStats(),
        ]);
        if (complaints.status === "fulfilled")
          setMyComplaints(complaints.value);
        if (statsRes.status === "fulfilled") setStats(statsRes.value);
      } else if (isAdmin) {
        const [complaints, adminStats] = await Promise.allSettled([
          complaintService.adminGetAllComplaints(),
          http.get("/admin/dashboard"),
        ]);
        if (complaints.status === "fulfilled")
          setAllComplaints(complaints.value);
        if (adminStats.status === "fulfilled") setStats(adminStats.value);
      }
    } catch {
      // API errors handled gracefully — leave state empty
    } finally {
      setLoading(false);
    }
  }, [isAdmin, isCitizen]);

  useEffect(() => {
    const timer = setTimeout(() => loadData(), 0);
    return () => clearTimeout(timer);
  }, [loadData]);

  if (loading) {
    return (
      <div className="dashboard__loading">
        <Spinner size="lg" />
        <p>Loading your dashboard...</p>
      </div>
    );
  }

  const firstName = user?.fullName?.split(" ")[0] || "there";

  return (
    <div className="dashboard">
      <div className="dashboard__header">
        <div>
          <h1 className="dashboard__title">Welcome back, {firstName} 👋</h1>
          <p className="dashboard__subtitle">
            {isAdmin
              ? "Here's an overview of all civic complaints."
              : "Here's an overview of your reported issues."}
          </p>
        </div>
        {isCitizen && (
          <Link to="/complaints" className="dashboard__cta-inline">
            + Report New
          </Link>
        )}
      </div>

      {stats && (
        <div className="dashboard__stats">
          <StatCard
            icon="📄"
            label="Total Complaints"
            value={stats.totalComplaints ?? "—"}
            tone="info"
          />
          <StatCard
            icon="⏳"
            label="Pending"
            value={stats.pendingComplaints ?? stats.pending ?? "—"}
            tone="warning"
          />
          <StatCard
            icon="🔍"
            label="Under Review"
            value={stats.underReviewComplaints ?? stats.underReview ?? "—"}
            tone="default"
          />
          <StatCard
            icon="⚙️"
            label="In Progress"
            value={stats.inProgressComplaints ?? stats.inProgress ?? "—"}
            tone="accent"
          />
          <StatCard
            icon="✅"
            label="Resolved"
            value={stats.resolvedComplaints ?? stats.resolved ?? "—"}
            tone="success"
          />
          <StatCard
            icon="🚫"
            label="Rejected"
            value={stats.rejectedComplaints ?? stats.rejected ?? "—"}
            tone="danger"
          />
        </div>
      )}

      <section className="dashboard__complaints">
        <SectionHeading
          align="left"
          eyebrow={isAdmin ? "All Complaints" : "My Complaints"}
          title={isAdmin ? "Manage civic issues" : "Your reported issues"}
        />

        {isAdmin && allComplaints.length === 0 && (
          <GlassCard className="dashboard__empty">
            <p>No complaints have been filed yet.</p>
          </GlassCard>
        )}

        {isCitizen && myComplaints.length === 0 && (
          <GlassCard className="dashboard__empty">
            <p>You haven't reported any complaints yet.</p>
            <Link to="/complaints">Report your first issue →</Link>
          </GlassCard>
        )}

        <div className="dashboard__grid">
          {(isAdmin ? allComplaints : myComplaints).map((c) => (
            <ComplaintCard key={c.id} complaint={c} />
          ))}
        </div>
      </section>
    </div>
  );
}

export default Dashboard;
