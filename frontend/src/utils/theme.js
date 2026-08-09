const THEME_KEY = "aicip-theme";

export const THEMES = {
  DAY: "day",
  NIGHT: "night",
};

export function getStoredTheme() {
  return localStorage.getItem(THEME_KEY);
}

export function getSystemTheme() {
  return window.matchMedia("(prefers-color-scheme: dark)").matches
    ? THEMES.NIGHT
    : THEMES.DAY;
}

export function getInitialTheme() {
  return getStoredTheme() || getSystemTheme();
}

export function applyTheme(theme) {
  document.documentElement.setAttribute("data-theme", theme);
  localStorage.setItem(THEME_KEY, theme);
}

export function toggleTheme() {
  const currentTheme =
    document.documentElement.getAttribute("data-theme") || THEMES.NIGHT;

  const nextTheme = currentTheme === THEMES.NIGHT ? THEMES.DAY : THEMES.NIGHT;

  applyTheme(nextTheme);

  return nextTheme;
}
