import "./StatCard.css";

function StatCard({ icon, label, value, tone = "default" }) {
  return (
    <div className={`stat-card stat-card--${tone}`}>
      {icon && (
        <div className="stat-card__icon" aria-hidden="true">
          {icon}
        </div>
      )}
      <div className="stat-card__body">
        <span className="stat-card__value">{value}</span>
        <span className="stat-card__label">{label}</span>
      </div>
    </div>
  );
}

export default StatCard;
