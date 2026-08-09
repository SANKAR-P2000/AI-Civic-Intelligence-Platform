import http from "./api.js";

export const complaintService = {
  // Citizen — create a complaint
  createComplaint(payload) {
    return http.post("/complaints", payload);
  },

  // Citizen — get complaints submitted by the current user
  getMyComplaints() {
    return http.get("/complaints/my");
  },

  // Citizen — get the current user's complaint statistics
  getMyStats() {
    return http.get("/dashboard/mystats");
  },

  // Authenticated — track a complaint by ID
  trackComplaint(id) {
    return http.get(`/complaints/track/${id}`);
  },

  // Admin/Gov — get all complaints
  getAllComplaints() {
    return http.get("/complaints");
  },

  // Admin — all complaints
  adminGetAllComplaints() {
    return http.get("/admin/complaints");
  },

  // Admin — get complaint by id
  adminGetComplaint(id) {
    return http.get(`/admin/complaints/${id}`);
  },

  // Admin — filter by status
  adminGetByStatus(status) {
    return http.get(`/admin/complaints/status/${status}`);
  },

  // Admin — search
  adminSearch(keyword) {
    return http.get(
      `/admin/complaints/search?keyword=${encodeURIComponent(keyword)}`,
    );
  },

  // Admin — update status
  adminUpdateStatus(id, status) {
    return http.put(`/admin/complaints/${id}/status`, { status });
  },
};

export default complaintService;
