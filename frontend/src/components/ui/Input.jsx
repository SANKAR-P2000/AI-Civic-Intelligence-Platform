import "./Input.css";

function Input({
  label,
  name,
  type = "text",
  value,
  onChange,
  placeholder = "",
  required = false,
  disabled = false,
  error = "",
  helperText = "",
  className = "",
  id,
  ...rest
}) {
  const inputId = id || name;
  const errorId = `${inputId}-error`;
  const helperId = `${inputId}-helper`;

  const classes = [
    "aicip-field__input",
    error ? "aicip-field__input--error" : "",
    className,
  ]
    .filter(Boolean)
    .join(" ");

  return (
    <div className="aicip-field">
      {label && (
        <label className="aicip-field__label" htmlFor={inputId}>
          {label}

          {required && (
            <span className="aicip-field__required" aria-hidden="true">
              *
            </span>
          )}
        </label>
      )}

      <input
        id={inputId}
        name={name}
        type={type}
        value={value}
        onChange={onChange}
        placeholder={placeholder}
        required={required}
        disabled={disabled}
        className={classes}
        aria-invalid={error ? "true" : "false"}
        aria-describedby={error ? errorId : helperText ? helperId : undefined}
        {...rest}
      />

      {error ? (
        <p id={errorId} className="aicip-field__error" role="alert">
          {error}
        </p>
      ) : helperText ? (
        <p id={helperId} className="aicip-field__helper">
          {helperText}
        </p>
      ) : null}
    </div>
  );
}

export default Input;
