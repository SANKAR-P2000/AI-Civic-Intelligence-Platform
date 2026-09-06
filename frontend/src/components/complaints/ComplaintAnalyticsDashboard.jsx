import { useEffect, useState } from "react";
import GlassCard from "../ui/GlassCard.jsx";
import complaintService from "../../services/complaints.js";
import "./ComplaintAnalyticsDashboard.css";

function ComplaintAnalyticsDashboard() {
  const [analytics, setAnalytics] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    async function loadAnalytics() {
      try {
        setLoading(true);
        setError("");
        const data = await complaintService.getAnalyticsSummary();
        setAnalytics(data);
      } catch (err) {
        setError(err.response?.data?.message || "Failed to load analytics summary.");
      } finally {
        setLoading(false);
      }
    }
    loadAnalytics();
  }, []);

  if (loading) {
    return (
      <GlassCard className="aicip-analytics-card">
        <div className="aicip-analytics-loading">Loading real-time complaint analytics...</div>
      </GlassCard>
    );
  }

  if (error) {
    return (
      <GlassCard className="aicip-analytics-card">
        <div className="aicip-analytics-error">{error}</div>
      </GlassCard>
    );
  }

  const total = analytics?.totalComplaints || 0;
  const statusEntries = Object.entries(analytics?.statusBreakdown || {});
  const categoryEntries = Object.entries(analytics?.categoryBreakdown || {});
  const dateEntries = Object.entries(analytics?.dateTrends || {}).slice(0, 7);

  return (
    <div className="aicip-analytics-dashboard">
      {/* Metric Cards */}
      <div className="aicip-metrics-grid">
        <GlassCard className="aicip-metric-card total">
          <span className="metric-label">Total Complaints</span>
          <span className="metric-value">{analytics?.totalComplaints || 0}</span>
          <span className="metric-sub">Logged in System</span>
        </GlassCard>

        <GlassCard className="aicip-metric-card pending">
          <span className="metric-label">Pending</span>
          <span className="metric-value">{analytics?.pendingComplaints || 0}</span>
          <span className="metric-sub">Awaiting Assignment</span>
        </GlassCard>

        <GlassCard className="aicip-metric-card in-progress">
          <span className="metric-label">In Progress</span>
          <span className="metric-value">{analytics?.inProgressComplaints || 0}</span>
          <span className="metric-sub">Under Investigation</span>
        </GlassCard>

        <GlassCard className="aicip-metric-card resolved">
          <span className="metric-label">Resolved</span>
          <span className="metric-value">{analytics?.resolvedComplaints || 0}</span>
          <span className="metric-sub">Action Completed</span>
        </GlassCard>
      </div>

      {/* Visual Distributions */}
      <div className="aicip-charts-grid">
        {/* Status Distribution Bar */}
        <GlassCard className="aicip-chart-card">
          <h3>Complaints by Status</h3>
          <div className="aicip-bar-list">
            {statusEntries.map(([status, count]) => {
              const percentage = total > 0 ? Math.round((count / total) * 100) : 0;
              return (
                <div key={status} className="aicip-bar-item">
                  <div className="bar-header">
                    <span className="bar-title">{status.replace(/_/g, " ")}</span>
                    <span className="bar-count">
                      {count} ({percentage}%)
                    </span>
                  </div>
                  <div className="bar-track">
                    <div
                      className={`bar-fill status-${status.toLowerCase()}`}
                      style={{ width: `${percentage}%` }}
                    />
                  </div>
                </div>
              );
            })}
          </div>
        </GlassCard>

        {/* Category Breakdown */}
        <GlassCard className="aicip-chart-card">
          <h3>Complaints by Category</h3>
          <div className="aicip-bar-list">
            {categoryEntries.map(([category, count]) => {
              const percentage = total > 0 ? Math.round((count / total) * 100) : 0;
              return (
                <div key={category} className="aicip-bar-item">
                  <div className="bar-header">
                    <span className="bar-title">
                      {category.toLowerCase().replace(/_/g, " ")}
                    </span>
                    <span className="bar-count">
                      {count} ({percentage}%)
                    </span>
                  </div>
                  <div className="bar-track">
                    <div
                      className="bar-fill category"
                      style={{ width: `${percentage}%` }}
                    />
                  </div>
                </div>
              );
            })}
          </div>
        </GlassCard>

        {/* Date Trends */}
        {dateEntries.length > 0 && (
          <GlassCard className="aicip-chart-card full-width">
            <h3>Recent Submission Trends</h3>
            <div className="aicip-trend-bars">
              {dateEntries.map(([dateStr, count]) => {
                const maxVal = Math.max(...dateEntries.map((d) => d[1]), 1);
                const heightPct = Math.round((count / maxVal) * 100);
                return (
                  <div key={dateStr} className="aicip-trend-col">
                    <div className="trend-bar-wrapper">
                      <div
                        className="trend-bar-fill"
                        style={{ height: `${Math.max(10, heightPct)}%` }}
                        title={`${count} complaints on ${dateStr}`}
                      />
                    </div>
                    <span className="trend-date">{dateStr.slice(5)}</span>
                    <span className="trend-count">{count}</span>
                  </div>
                );
              })}
            </div>
          </GlassCard>
        )}
      </div>
    </div>
  );
}

export default ComplaintAnalyticsDashboard;
