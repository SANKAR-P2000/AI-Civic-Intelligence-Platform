import Pill from "./Pill.jsx";
import "./ComplaintCard.css";

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

function ComplaintCard({ complaint, onView }) {
  const {
    id,
    title,
    description,
    category,
    status,
    location,
    imageUrl,
    citizenName,
    createdAt,
  } = complaint;

  return (
    <article className="complaint-card">
      <div className="complaint-card__top">
        <Pill>{status}</Pill>
        <span className="complaint-card__id">#{id}</span>
      </div>

      {imageUrl && (
        <img
          className="complaint-card__image"
          src={imageUrl}
          alt={title}
          loading="lazy"
        />
      )}

      <div className="complaint-card__body">
        <h3 className="complaint-card__title">{title}</h3>
        <p className="complaint-card__desc">{description}</p>

        <div className="complaint-card__meta">
          <span className="complaint-card__meta-item">
            <span aria-hidden="true">🗂️</span> {formatCategory(category)}
          </span>
          {location && (
            <span className="complaint-card__meta-item">
              <span aria-hidden="true">📍</span> {location}
            </span>
          )}
          {citizenName && (
            <span className="complaint-card__meta-item">
              <span aria-hidden="true">👤</span> {citizenName}
            </span>
          )}
          <span className="complaint-card__meta-item">
            <span aria-hidden="true">🕒</span> {formatDate(createdAt)}
          </span>
        </div>
      </div>

      {onView && (
        <button type="button" className="complaint-card__cta" onClick={onView}>
          View details
        </button>
      )}
    </article>
  );
}

export default ComplaintCard;
