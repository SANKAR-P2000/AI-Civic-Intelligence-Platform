import { useState } from "react";
import { Link, useNavigate } from "react-router";
import Button from "../components/ui/Button.jsx";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import StatCard from "../components/ui/StatCard.jsx";
import Input from "../components/ui/Input.jsx";
import complaintService from "../services/complaints.js";
import "./Home.css";

const FEATURES = [
  {
    icon: "🚨",
    title: "Report Instantly",
    desc: "Log civic issues like road damage, water supply, or street lights in under a minute with photos and location.",
  },
  {
    icon: "📊",
    title: "Transparent Tracking",
    desc: "Track your complaint's live status — from pending to resolved — with complete transparency and accountability.",
  },
  {
    icon: "🤖",
    title: "AI-Powered Insights",
    desc: "Our intelligence layer analyzes patterns to help authorities prioritize and resolve issues faster.",
  },
  {
    icon: "🏛️",
    title: "Government Integration",
    desc: "Seamlessly connects citizens with local governing bodies for swift, organized, measurable civic action.",
  },
];

const STEPS = [
  {
    num: "01",
    title: "Create an account",
    desc: "Sign up with your details in seconds and get started.",
  },
  {
    num: "02",
    title: "Report an issue",
    desc: "Describe the problem, pick a category, and add a photo.",
  },
  {
    num: "03",
    title: "Track progress",
    desc: "Follow your complaint through real-time status updates.",
  },
  {
    num: "04",
    title: "See it resolved",
    desc: "Watch authorities take action and your community improve.",
  },
];

function Home() {
  const navigate = useNavigate();
  const [trackId, setTrackId] = useState("");
  const [trackError, setTrackError] = useState("");
  const [tracking, setTracking] = useState(false);

  const handleTrack = async (e) => {
    e.preventDefault();
    setTrackError("");
    if (!trackId.trim()) {
      setTrackError("Please enter a complaint ID to track.");
      return;
    }
    setTracking(true);
    try {
      const complaint = await complaintService.trackComplaint(trackId.trim());
      navigate(`/complaints?track=${complaint.id}`);
    } catch {
      setTrackError("We couldn't find that complaint. Please check the ID.");
    } finally {
      setTracking(false);
    }
  };

  return (
    <div className="home">
      {/* HERO */}
      <section className="home__hero">
        <div className="home__hero-text">
          <span className="home__eyebrow">
            <span className="home__eyebrow-dot" /> AI Civic Intelligence
            Platform
          </span>
          <h1 className="home__title">
            Shape a <span className="home__title-accent">smarter</span>, safer
            community
          </h1>
          <p className="home__subtitle">
            Report civic issues, track resolutions, and drive real change in
            your city — all in one intelligent platform.
          </p>
          <div className="home__hero-actions">
            <Link to="/complaints">
              <Button size="lg">🚨 Report a Complaint</Button>
            </Link>
            <Link to="/register">
              <Button size="lg" variant="secondary">
                Join the Platform
              </Button>
            </Link>
          </div>

          <form className="home__track" onSubmit={handleTrack}>
            <Input
              name="trackId"
              placeholder="Enter complaint ID to track status"
              value={trackId}
              onChange={(e) => setTrackId(e.target.value)}
              aria-label="Track complaint ID"
            />
            <Button type="submit" disabled={tracking}>
              {tracking ? "Tracking..." : "Track"}
            </Button>
          </form>
          {trackError && <p className="home__track-error">{trackError}</p>}
        </div>

        <div className="home__hero-visual" aria-hidden="true">
          <GlassCard className="home__hero-card">
            <div className="home__hero-card-top">
              <span className="home__hero-badge">Live Status</span>
              <span className="home__hero-pill-back">
                <span className="home__hero-pill home__hero-pill--ok">
                  Resolved
                </span>
              </span>
            </div>
            <div className="home__hero-progress">
              <div className="home__hero-progress-bar" />
            </div>
            <div className="home__hero-stats">
              <div>
                <strong>1,284</strong>
                <span>Resolved</span>
              </div>
              <div>
                <strong>96%</strong>
                <span>Satisfaction</span>
              </div>
              <div>
                <strong>247</strong>
                <span>In Progress</span>
              </div>
            </div>
          </GlassCard>
        </div>
      </section>

      {/* STATS */}
      <section className="home__stats">
        <StatCard
          icon="📄"
          label="Complaints Filed"
          value="12,480+"
          tone="info"
        />
        <StatCard icon="✅" label="Resolved" value="9,310" tone="success" />
        <StatCard
          icon="⚡"
          label="Avg. Resolution"
          value="3.2 days"
          tone="warning"
        />
        <StatCard
          icon="👥"
          label="Active Citizens"
          value="8,740"
          tone="default"
        />
      </section>

      {/* FEATURES */}
      <section className="home__section">
        <SectionHeading
          eyebrow="Why AICIP"
          title="Everything you need to drive civic change"
          subtitle="From reporting to resolution, we make civic engagement effortless, transparent, and impactful."
        />
        <div className="home__features">
          {FEATURES.map((f) => (
            <GlassCard key={f.title} className="home__feature">
              <div className="home__feature-icon">{f.icon}</div>
              <h3 className="home__feature-title">{f.title}</h3>
              <p className="home__feature-desc">{f.desc}</p>
            </GlassCard>
          ))}
        </div>
      </section>

      {/* HOW IT WORKS */}
      <section className="home__section">
        <SectionHeading
          eyebrow="How it works"
          title="From issue to resolution in four simple steps"
        />
        <div className="home__steps">
          {STEPS.map((s) => (
            <div className="home__step" key={s.num}>
              <span className="home__step-num">{s.num}</span>
              <h3 className="home__step-title">{s.title}</h3>
              <p className="home__step-desc">{s.desc}</p>
            </div>
          ))}
        </div>
      </section>

      {/* CTA */}
      <section className="home__cta">
        <GlassCard className="home__cta-card">
          <h2 className="home__cta-title">Ready to make a difference?</h2>
          <p className="home__cta-subtitle">
            Join thousands of citizens building a smarter community today.
          </p>
          <div className="home__cta-actions">
            <Link to="/register">
              <Button size="lg">Get Started Free</Button>
            </Link>
            <Link to="/services">
              <Button size="lg" variant="ghost">
                Explore Services
              </Button>
            </Link>
          </div>
        </GlassCard>
      </section>
    </div>
  );
}

export default Home;
