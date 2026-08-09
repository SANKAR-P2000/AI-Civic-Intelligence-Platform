import "./Spinner.css";

export function Spinner({ size = "md", label }) {
  return (
    <span
      className={`spinner spinner--${size}`}
      role="status"
      aria-label={label || "Loading"}
    >
      <span className="spinner__dot" aria-hidden="true" />
    </span>
  );
}

export default Spinner;
