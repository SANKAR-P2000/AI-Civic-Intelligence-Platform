import "./Pill.css";

const STATUS_TONES = {
  PENDING: "warning",
  UNDER_REVIEW: "info",
  IN_PROGRESS: "accent",
  RESOLVED: "success",
  REJECTED: "danger",
};

function Pill({ children, tone = "neutral", className = "" }) {
  const resolvedTone = STATUS_TONES[children] || tone;
  return (
    <span className={`pill pill--${resolvedTone} ${className}`}>
      {children}
    </span>
  );
}

export default Pill;
