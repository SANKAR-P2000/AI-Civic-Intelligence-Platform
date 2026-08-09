// Centralized API client with JWT authentication support.
// Works both in dev (via Vite proxy) and production (via same-origin /api).

const API_BASE = import.meta.env.VITE_API_BASE_URL || "/api";

const TOKEN_KEY = "aicip-access-token";
const REFRESH_TOKEN_KEY = "aicip-refresh-token";

export const tokenStore = {
  getAccessToken: () => localStorage.getItem(TOKEN_KEY),
  getRefreshToken: () => localStorage.getItem(REFRESH_TOKEN_KEY),
  setTokens: (accessToken, refreshToken) => {
    if (accessToken) localStorage.setItem(TOKEN_KEY, accessToken);
    if (refreshToken) localStorage.setItem(REFRESH_TOKEN_KEY, refreshToken);
  },
  setAccessToken: (token) => localStorage.setItem(TOKEN_KEY, token),
  clear: () => {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(REFRESH_TOKEN_KEY);
  },
};

export class ApiError extends Error {
  constructor(message, status, data) {
    super(message);
    this.name = "ApiError";
    this.status = status;
    this.data = data;
  }
}

async function parseError(response) {
  let data;
  try {
    data = await response.json();
  } catch {
    data = null;
  }

  let message = "Something went wrong. Please try again.";
  if (data) {
    if (typeof data.message === "string") message = data.message;
    else if (data.error) message = data.error;
    else if (Array.isArray(data.errors)) {
      message = data.errors
        .map((e) => e?.message || e?.defaultMessage || String(e))
        .join(", ");
    }
  }

  if (!response.ok && response.status >= 500) {
    message = "Server error. Please try again later.";
  }

  return new ApiError(message, response.status, data);
}

let refreshPromise = null;

async function refreshAccessToken() {
  const refreshToken = tokenStore.getRefreshToken();
  if (!refreshToken) return null;

  const res = await fetch(`${API_BASE}/auth/refresh`, {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ refreshToken }),
  });

  if (!res.ok) {
    tokenStore.clear();
    return null;
  }

  const data = await res.json();
  tokenStore.setAccessToken(data.accessToken || data.token);
  return data.accessToken || data.token;
}

/**
 * Perform an authenticated fetch request.
 * Automatically attaches the JWT and handles token refresh on 401.
 */
export async function apiFetch(path, options = {}) {
  const { headers = {}, _retry = false, ...rest } = options;

  const token = tokenStore.getAccessToken();

  const fetchOptions = {
    ...rest,
    headers: {
      "Content-Type": "application/json",
      ...(token ? { Authorization: `Bearer ${token}` } : {}),
      ...headers,
    },
  };

  let response;
  try {
    response = await fetch(`${API_BASE}${path}`, fetchOptions);
  } catch {
    throw new ApiError("Network error. Please check your connection.", 0);
  }

  // Handle unauthorized — attempt refresh once
  if (response.status === 401 && token && !_retry) {
    if (!refreshPromise) {
      refreshPromise = refreshAccessToken().finally(() => {
        refreshPromise = null;
      });
    }
    const newToken = await refreshPromise;
    if (newToken) {
      return apiFetch(path, { ...options, _retry: true });
    }
  }

  if (!response.ok) {
    throw await parseError(response);
  }

  // 204 No Content
  if (response.status === 204) return null;

  const contentType = response.headers.get("content-type") || "";
  if (contentType.includes("application/json")) {
    return response.json();
  }
  return response.text();
}

export const http = {
  get: (path, options) => apiFetch(path, { method: "GET", ...options }),
  post: (path, body, options) =>
    apiFetch(path, {
      method: "POST",
      body: body ? JSON.stringify(body) : undefined,
      ...options,
    }),
  put: (path, body, options) =>
    apiFetch(path, {
      method: "PUT",
      body: body ? JSON.stringify(body) : undefined,
      ...options,
    }),
  delete: (path, options) => apiFetch(path, { method: "DELETE", ...options }),
};

export default http;
