import { Link } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import Button from "../components/ui/Button.jsx";
import "./About.css";

const VALUES = [
  {
    icon: "🤝",
    title: "Collaboration",
    desc: "We connect citizens and government to work toward shared community goals.",
  },
  {
    icon: "🔍",
    title: "Transparency",
    desc: "Every complaint and resolution is visible, measurable, and accountable.",
  },
  {
    icon: "⚡",
    title: "Innovation",
    desc: "We use AI to make civic engagement smarter, faster, and more effective.",
  },
  {
    icon: "🌱",
    title: "Impact",
    desc: "We measure our success by the real, lasting change we drive in communities.",
  },
];

const STATS = [
  { value: "12K+", label: "Complaints Filed" },
  { value: "9K+", label: "Issues Resolved" },
  { value: "50+", label: "Cities Served" },
  { value: "8K+", label: "Active Citizens" },
];

function About() {
  return (
    <div className="about">
      <section className="about__hero">
        <SectionHeading
          eyebrow="About AICIP"
          title="Building smarter, safer communities together"
          subtitle="The AI Civic Intelligence Platform empowers citizens to report issues and authorities to resolve them — transparently and efficiently."
        />
        <div className="about__stats">
          {STATS.map((s) => (
            <GlassCard key={s.label} className="about__stat">
              <strong>{s.value}</strong>
              <span>{s.label}</span>
            </GlassCard>
          ))}
        </div>
      </section>

      <section className="about__mission">
        <GlassCard className="about__mission-card">
          <h2 className="about__mission-title">Our Mission</h2>
          <p>
            To transform civic engagement by giving every citizen a clear,
            AI-powered voice — and every government the tools to listen, act,
            and deliver measurable results to the communities they serve.
          </p>
        </GlassCard>
      </section>

      <section className="about__values">
        <SectionHeading eyebrow="Our Values" title="What drives us every day" />
        <div className="about__values-grid">
          {VALUES.map((v) => (
            <GlassCard key={v.title} className="about__value">
              <div className="about__value-icon">{v.icon}</div>
              <h3>{v.title}</h3>
              <p>{v.desc}</p>
            </GlassCard>
          ))}
        </div>
      </section>

      <section className="about__cta">
        <GlassCard className="about__cta-card">
          <h2>Join us in shaping the future of your community</h2>
          <p>Create your account and be part of the change.</p>
          <Link to="/register">
            <Button size="lg">Get Started</Button>
          </Link>
        </GlassCard>
      </section>
    </div>
  );
}

export default About;
