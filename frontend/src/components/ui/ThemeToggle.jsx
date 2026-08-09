import { useEffect, useState } from "react";
import { applyTheme, getInitialTheme, THEMES } from "../../utils/theme.js";
import "./ThemeToggle.css";

function ThemeToggle() {
  const [theme, setTheme] = useState(getInitialTheme());

  useEffect(() => {
    applyTheme(theme);
  }, [theme]);

  const isNight = theme === THEMES.NIGHT;

  const handleToggle = () => {
    setTheme(isNight ? THEMES.DAY : THEMES.NIGHT);
  };

  return (
    <button
      type="button"
      className="theme-toggle"
      onClick={handleToggle}
      aria-label={isNight ? "Switch to daylight mode" : "Switch to night mode"}
      title={isNight ? "Switch to daylight mode" : "Switch to night mode"}
    >
      <span aria-hidden="true">{isNight ? "☀️" : "🌙"}</span>

      <span>{isNight ? "Daylight" : "Night"}</span>
    </button>
  );
}

export default ThemeToggle;
