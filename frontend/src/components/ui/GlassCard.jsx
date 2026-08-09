import "./GlassCard.css";

function GlassCard({ children, className = "", padding = "md", hover = true }) {
  const classes = [
    "glass-card",
    `glass-card--padding-${padding}`,
    hover ? "glass-card--hover" : "",
    className,
  ]
    .filter(Boolean)
    .join(" ");

  return <div className={classes}>{children}</div>;
}

export default GlassCard;
