import { Link } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import Button from "../components/ui/Button.jsx";
import "./Services.css";

const SERVICES = [
  {
    icon: "🚨",
    title: "Issue Reporting",
    desc: "Report a wide range of civic issues quickly with photos, categories, and precise locations.",
    points: ["10+ issue categories", "Photo attachments", "One-tap submission"],
  },
  {
    icon: "📡",
    title: "Live Tracking",
    desc: "Follow your complaint through every stage, from submission to resolution, in real time.",
    points: ["Real-time status", "Transparent pipeline", "Email notifications"],
  },
  {
    icon: "🤖",
    title: "AI Analytics",
    desc: "Get smart insights on recurring issues to help authorities prioritize civic action.",
    points: ["Pattern detection", "Priority scoring", "Trend reports"],
  },
  {
    icon: "🏛️",
    title: "Government Portal",
    desc: "A dedicated interface for administrators and officials to manage and resolve complaints.",
    points: ["Role-based access", "Status management", "Search & filters"],
  },
  {
    icon: "📊",
    title: "Civic Dashboard",
    desc: "Comprehensive dashboards with key statistics to measure community health and progress.",
    points: ["KPI overview", "Category stats", "Resolution trends"],
  },
  {
    icon: "🔔",
    title: "Smart Notifications",
    desc: "Stay informed with automatic updates on every change to your reported issues.",
    points: ["Instant alerts", "Status changes", "Resolution confirmations"],
  },
];

function Services() {
  return (
    <div className="services">
      <SectionHeading
        eyebrow="Our Services"
        title="Powerful tools for civic engagement"
        subtitle="Everything we offer to make reporting, tracking, and resolving community issues effortless."
      />

      <div className="services__grid">
        {SERVICES.map((s) => (
          <GlassCard key={s.title} className="services__card">
            <div className="services__icon">{s.icon}</div>
            <h3 className="services__title">{s.title}</h3>
            <p className="services__desc">{s.desc}</p>
            <ul className="services__points">
              {s.points.map((p) => (
                <li key={p}>
                  <span aria-hidden="true">✓</span> {p}
                </li>
              ))}
            </ul>
          </GlassCard>
        ))}
      </div>

      <section className="services__cta">
        <GlassCard className="services__cta-card">
          <h2>Start using these services today</h2>
          <p>Join the platform and help shape a smarter community.</p>
          <div className="services__cta-actions">
            <Link to="/register">
              <Button size="lg">Get Started</Button>
            </Link>
            <Link to="/complaints">
              <Button size="lg" variant="secondary">
                Track a Complaint
              </Button>
            </Link>
          </div>
        </GlassCard>
      </section>
    </div>
  );
}

export default Services;
