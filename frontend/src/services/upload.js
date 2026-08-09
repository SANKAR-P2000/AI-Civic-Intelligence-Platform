import { tokenStore } from "./api.js";

const API_BASE = import.meta.env.VITE_API_BASE_URL || "/api";

/**
 * Upload a complaint image (JPG/PNG, max 5MB).
 * Returns the stored filename (relative) which should be passed
 * as `imageUrl` when creating a complaint.
 */
export async function uploadComplaintImage(file) {
  const token = tokenStore.getAccessToken();

  const formData = new FormData();
  formData.append("file", file);

  const response = await fetch(`${API_BASE}/files/upload`, {
    method: "POST",
    headers: token ? { Authorization: `Bearer ${token}` } : {},
    body: formData,
  });

  if (!response.ok) {
    let message = "Image upload failed. Please try again.";
    try {
      const data = await response.json();
      if (data?.message) message = data.message;
      else if (data?.error) message = data.error;
    } catch {
      // ignore parse errors
    }
    throw new Error(message);
  }

  return response.text();
}

export default uploadComplaintImage;
