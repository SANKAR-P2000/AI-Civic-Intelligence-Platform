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

  async forgotPassword(email) {
    return http.post("/auth/forgot-password", { email });
  },

  async verifyOtp(email, otp) {
    return http.post("/auth/verify-otp", { email, otp });
  },

  async resendOtp(email) {
    return http.post("/auth/resend-otp", { email });
  },

  async resetPassword(email, resetToken, newPassword, confirmPassword) {
    return http.post("/auth/reset-password", {
      email,
      resetToken,
      newPassword,
      confirmPassword,
    });
  },
};

export default authService;
