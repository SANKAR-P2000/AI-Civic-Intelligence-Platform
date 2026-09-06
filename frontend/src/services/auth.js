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

  async getProfile() {
    return http.get("/users/profile");
  },

  async updateProfile({ fullName, phoneNumber }) {
    return http.patch("/users/profile", { fullName, phoneNumber });
  },

  async changePassword({ currentPassword, newPassword, confirmPassword }) {
    return http.patch("/users/change-password", {
      currentPassword,
      newPassword,
      confirmPassword,
    });
  },

  async uploadProfilePicture(file) {
    const formData = new FormData();
    formData.append("file", file);
    return http.post("/users/profile/picture", formData);
  },

  async sendVerificationOtp(email) {
    return http.post("/auth/send-verification-otp", { email });
  },

  async verifyEmail(email, otp) {
    return http.post("/auth/verify-email", { email, otp });
  },

  async resendEmailOtp(email) {
    return http.post("/auth/resend-email-otp", { email });
  },
};

export default authService;
