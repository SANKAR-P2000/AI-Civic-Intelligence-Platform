import { useEffect, useState } from "react";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import { Spinner } from "../components/ui/Spinner.jsx";
import analyticsService from "../services/analytics.js";
import "./AdminAnalytics.css";

function formatLabel(value = "") {
  return value.toLowerCase().replace(/_/g, " ");
}

function BarRow({ label, value, max }) {
  const pct = max > 0 ? Math.round((value / max) * 100) : 0;
  return (
    <div className="admin-analytics__bar-row">
      <span className="admin-analytics__bar-label">{formatLabel(label)}</span>
      <div className="admin-analytics__bar-track">
        <div
          className="admin-analytics__bar-fill"
          style={{ width: `${pct}%` }}
        />
      </div>
      <span className="admin-analytics__bar-value">{value}</span>
    </div>
  );
}

function ChartCard({ title, data }) {
  const max = data.reduce((m, d) => Math.max(m, d.count), 0);
  return (
    <GlassCard className="admin-analytics__chart">
      <h3 className="admin-analytics__chart-title">{title}</h3>
      {data.length === 0 ? (
        <p className="admin-analytics__empty">No data available yet.</p>
      ) : (
        <div className="admin-analytics__bars">
          {data.map((d) => (
            <BarRow key={d.label} label={d.label} value={d.count} max={max} />
          ))}
        </div>
      )}
    </GlassCard>
  );
}

function AdminAnalytics() {
  const [category, setCategory] = useState([]);
  const [status, setStatus] = useState([]);
  const [location, setLocation] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");

  useEffect(() => {
    let active = true;
    (async () => {
      try {
        const [cat, stat, loc] = await Promise.all([
          analyticsService.getCategoryAnalytics(),
          analyticsService.getStatusAnalytics(),
          analyticsService.getLocationAnalytics(),
        ]);
        if (active) {
          setCategory(cat);
          setStatus(stat);
          setLocation(loc);
        }
      } catch (err) {
        if (active) setError(err.message || "Failed to load analytics.");
      } finally {
        if (active) setLoading(false);
      }
    })();
    return () => {
      active = false;
    };
  }, []);

  if (loading) {
    return (
      <div className="admin-analytics__loading">
        <Spinner size="lg" />
        <p>Loading analytics...</p>
      </div>
    );
  }

  return (
    <div className="admin-analytics">
      <SectionHeading
        eyebrow="Admin Panel"
        title="Complaint Analytics"
        subtitle="Insights into how complaints are distributed across categories, statuses, and locations."
      />

      {error && <p className="admin-analytics__error">{error}</p>}

      <div className="admin-analytics__grid">
        <ChartCard title="By Category" data={category} />
        <ChartCard title="By Status" data={status} />
        <ChartCard title="By Location" data={location} />
      </div>
    </div>
  );
}

export default AdminAnalytics;
