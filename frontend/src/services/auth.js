import http, { tokenStore } from "./api.js";

export const authService = {
  async register({ fullName, email, password, phoneNumber }) {
    const data = await http.post("/users/register", {
      fullName,
      email,
      password,
      phoneNumber,
    });
    return data;
  },

  async login({ email, password }) {
    const data = await http.post("/users/login", { email, password });
    // Store tokens for later authenticated requests
    tokenStore.setTokens(data.token, data.refreshToken);
    return data;
  },

  async getCurrentUser() {
    return http.get("/users/me");
  },

  async logout(refreshToken) {
    try {
      await http.post("/auth/logout", { refreshToken });
    } catch {
      // Ignore logout API errors — clear local session anyway
    } finally {
      tokenStore.clear();
    }
  },
};

export default authService;
