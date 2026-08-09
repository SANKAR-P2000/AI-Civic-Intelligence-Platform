import http from "./api.js";

export const analyticsService = {
  // Admin — complaint analytics grouped by category
  getCategoryAnalytics() {
    return http.get("/admin/analytics/category");
  },

  // Admin — complaint analytics grouped by status
  getStatusAnalytics() {
    return http.get("/admin/analytics/status");
  },

  // Admin — complaint analytics grouped by location
  getLocationAnalytics() {
    return http.get("/admin/analytics/location");
  },

  // Admin — complaint analytics grouped by date
  getDateAnalytics() {
    return http.get("/admin/analytics/date");
  },
};

export default analyticsService;
