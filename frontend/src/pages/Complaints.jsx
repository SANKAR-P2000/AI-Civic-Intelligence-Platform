import { useCallback, useEffect, useState } from "react";
import { Link, useSearchParams } from "react-router";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import GlassCard from "../components/ui/GlassCard.jsx";
import SectionHeading from "../components/ui/SectionHeading.jsx";
import Pill from "../components/ui/Pill.jsx";
import { useAuth } from "../hooks/useAuth.js";
import complaintService from "../services/complaints.js";
import uploadComplaintImage from "../services/upload.js";
import ComplaintSearchFilter from "../components/complaints/ComplaintSearchFilter.jsx";
import ComplaintAnalyticsDashboard from "../components/complaints/ComplaintAnalyticsDashboard.jsx";
import "./Complaints.css";

const CATEGORIES = [
  "ROAD_DAMAGE",
  "STREET_LIGHT",
  "GARBAGE",
  "WATER_SUPPLY",
  "DRAINAGE",
  "TRAFFIC",
  "PUBLIC_TRANSPORT",
  "ENVIRONMENT",
  "ANIMAL_CONTROL",
  "OTHER",
];

const EMPTY_FORM = {
  title: "",
  description: "",
  category: "",
  location: "",
  latitude: "",
  longitude: "",
};

function Complaints() {
  const { isAuthenticated } = useAuth();
  const [searchParams, setSearchParams] = useSearchParams();
  const trackParam = searchParams.get("track");

  // Tab State: 'search', 'analytics', 'track', 'submit'
  const [activeTab, setActiveTab] = useState(trackParam ? "track" : "search");

  // Track state
  const [trackId, setTrackId] = useState(trackParam || "");
  const [tracked, setTracked] = useState(null);
  const [trackLoading, setTrackLoading] = useState(false);
  const [trackError, setTrackError] = useState("");

  // Search & Filter state
  const [searchResults, setSearchResults] = useState(null);
  const [searchLoading, setSearchLoading] = useState(false);
  const [searchError, setSearchError] = useState("");
  const [activeFilters, setActiveFilters] = useState({});
  const [currentPage, setCurrentPage] = useState(0);
  const [selectedComplaint, setSelectedComplaint] = useState(null);

  // Submit state
  const [form, setForm] = useState(EMPTY_FORM);
  const [imageFile, setImageFile] = useState(null);
  const [imagePreview, setImagePreview] = useState("");
  const [submitLoading, setSubmitLoading] = useState(false);
  const [submitError, setSubmitError] = useState("");
  const [submitSuccess, setSubmitSuccess] = useState(null);

  const fetchTrack = useCallback(async (id) => {
    setTrackLoading(true);
    setTrackError("");
    setTracked(null);
    try {
      const data = await complaintService.trackComplaint(id);
      setTracked(data);
    } catch {
      setTrackError(
        "Unable to track this complaint. The ID may be invalid or access is restricted."
      );
    } finally {
      setTrackLoading(false);
    }
  }, []);

  useEffect(() => {
    if (!trackParam) return undefined;
    setActiveTab("track");
    const timer = setTimeout(() => fetchTrack(trackParam), 0);
    return () => clearTimeout(timer);
  }, [trackParam, fetchTrack]);

  // Execute Search API call
  const executeSearch = useCallback(
    async (filters = activeFilters, page = currentPage) => {
      try {
        setSearchLoading(true);
        setSearchError("");

        let res;
        if (filters.isSpatial) {
          res = await complaintService.searchNearbyComplaints({
            latitude: filters.latitude,
            longitude: filters.longitude,
            radiusKm: filters.radiusKm || 5,
            page,
            size: 10,
          });
        } else {
          res = await complaintService.searchComplaints({
            ...filters,
            page,
            size: 10,
          });
        }
        setSearchResults(res);
      } catch (err) {
        setSearchError(
          err.response?.data?.message || "Failed to search complaints."
        );
      } finally {
        setSearchLoading(false);
      }
    },
    [activeFilters, currentPage]
  );

  useEffect(() => {
    if (activeTab === "search") {
      executeSearch(activeFilters, currentPage);
    }
  }, [activeTab, activeFilters, currentPage, executeSearch]);

  const handleApplyFilters = (filters) => {
    setActiveFilters(filters);
    setCurrentPage(0);
  };

  const handleClearFilters = () => {
    setActiveFilters({});
    setCurrentPage(0);
  };

  const handleTrack = (e) => {
    e.preventDefault();
    if (!trackId.trim()) {
      setTrackError("Please enter a complaint ID.");
      return;
    }
    setSearchParams({ track: trackId.trim() });
  };

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleImageChange = (e) => {
    const file = e.target.files?.[0];
    if (!file) {
      setImageFile(null);
      setImagePreview("");
      return;
    }
    setImageFile(file);
    setImagePreview(URL.createObjectURL(file));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setSubmitError("");
    setSubmitSuccess(null);

    if (!form.title || !form.description || !form.category || !form.location) {
      setSubmitError("Please fill in all required fields.");
      return;
    }

    setSubmitLoading(true);
    try {
      let imageUrl = null;

      if (imageFile) {
        try {
          imageUrl = await uploadComplaintImage(imageFile);
        } catch (uploadErr) {
          setSubmitError(uploadErr.message || "Image upload failed.");
          setSubmitLoading(false);
          return;
        }
      }

      const payload = {
        ...form,
        latitude: form.latitude ? parseFloat(form.latitude) : null,
        longitude: form.longitude ? parseFloat(form.longitude) : null,
        imageUrl,
      };

      const data = await complaintService.createComplaint(payload);
      setSubmitSuccess(data);
      setForm(EMPTY_FORM);
      setImageFile(null);
      setImagePreview("");
    } catch (err) {
      setSubmitError(err.message || "Failed to submit complaint.");
    } finally {
      setSubmitLoading(false);
    }
  };

  const formatCategory = (c = "") => c.toLowerCase().replace(/_/g, " ");

  return (
    <div className="complaints">
      {/* Top Header & Tab Navigation */}
      <section className="complaints__header">
        <SectionHeading
          eyebrow="Citizen Portal"
          title="AICIP Complaints & Civic Search"
          subtitle="Explore citizen complaints, analyze civic statistics, track status, or report a new issue."
        />

        <div className="complaints__tab-bar">
          <button
            type="button"
            className={`complaints__tab ${activeTab === "search" ? "active" : ""}`}
            onClick={() => setActiveTab("search")}
          >
            🔍 Search & Filter
          </button>
          <button
            type="button"
            className={`complaints__tab ${activeTab === "analytics" ? "active" : ""}`}
            onClick={() => setActiveTab("analytics")}
          >
            📊 Real-Time Analytics
          </button>
          <button
            type="button"
            className={`complaints__tab ${activeTab === "track" ? "active" : ""}`}
            onClick={() => setActiveTab("track")}
          >
            📍 Track Complaint
          </button>
          <button
            type="button"
            className={`complaints__tab ${activeTab === "submit" ? "active" : ""}`}
            onClick={() => setActiveTab("submit")}
          >
            ✏️ Report Issue
          </button>
        </div>
      </section>

      {/* TAB 1: SEARCH & FILTER */}
      {activeTab === "search" && (
        <section className="complaints__search-section">
          <ComplaintSearchFilter
            onApplyFilters={handleApplyFilters}
            onClearFilters={handleClearFilters}
            loading={searchLoading}
          />

          {searchError && (
            <GlassCard className="complaints__error-card">{searchError}</GlassCard>
          )}

          {searchLoading ? (
            <GlassCard className="complaints__loading-card">
              Loading complaints data from server...
            </GlassCard>
          ) : searchResults && searchResults.content.length > 0 ? (
            <>
              <div className="complaints__grid">
                {searchResults.content.map((complaint) => (
                  <GlassCard key={complaint.id} className="complaint-card">
                    <div className="complaint-card__head">
                      <span className="complaint-id">#{complaint.id}</span>
                      <Pill>{complaint.status}</Pill>
                    </div>

                    <h3 className="complaint-card__title">{complaint.title}</h3>
                    <p className="complaint-card__desc">
                      {complaint.description.length > 120
                        ? `${complaint.description.substring(0, 120)}...`
                        : complaint.description}
                    </p>

                    <div className="complaint-card__meta">
                      <span>🗂️ {formatCategory(complaint.category)}</span>
                      {complaint.location && <span>📍 {complaint.location}</span>}
                      {complaint.latitude && (
                        <span>
                          🌐 {complaint.latitude.toFixed(4)}, {complaint.longitude?.toFixed(4)}
                        </span>
                      )}
                      <span>🕒 {new Date(complaint.createdAt).toLocaleDateString()}</span>
                    </div>

                    <div className="complaint-card__footer">
                      <Button
                        size="xs"
                        variant="secondary"
                        onClick={() => setSelectedComplaint(complaint)}
                      >
                        View Details
                      </Button>
                    </div>
                  </GlassCard>
                ))}
              </div>

              {/* Database Pagination */}
              <div className="complaints__pagination">
                <Button
                  size="sm"
                  variant="ghost"
                  disabled={searchResults.first || searchLoading}
                  onClick={() => setCurrentPage((prev) => Math.max(0, prev - 1))}
                >
                  &laquo; Previous
                </Button>

                <span className="pagination-info">
                  Page {searchResults.pageNumber + 1} of {searchResults.totalPages} ({searchResults.totalElements} items)
                </span>

                <Button
                  size="sm"
                  variant="ghost"
                  disabled={searchResults.last || searchLoading}
                  onClick={() => setCurrentPage((prev) => prev + 1)}
                >
                  Next &raquo;
                </Button>
              </div>
            </>
          ) : (
            <GlassCard className="complaints__empty-card">
              <h3>No complaints found</h3>
              <p>Try adjusting your search keywords, category filters, or location parameters.</p>
            </GlassCard>
          )}
        </section>
      )}

      {/* TAB 2: REAL-TIME ANALYTICS */}
      {activeTab === "analytics" && (
        <section className="complaints__analytics-section">
          <ComplaintAnalyticsDashboard />
        </section>
      )}

      {/* TAB 3: TRACK COMPLAINT */}
      {activeTab === "track" && (
        <section className="complaints__track">
          <GlassCard className="complaints__track-card">
            <form className="complaints__track-form" onSubmit={handleTrack}>
              <Input
                name="trackId"
                label="Complaint ID"
                placeholder="e.g. 101"
                value={trackId}
                onChange={(e) => setTrackId(e.target.value)}
              />
              <Button type="submit" loading={trackLoading}>
                Track
              </Button>
            </form>
            {trackError && <p className="complaints__error">{trackError}</p>}
          </GlassCard>

          {tracked && (
            <GlassCard className="complaints__result">
              <div className="complaints__result-head">
                <div>
                  <span className="complaints__result-label">Complaint #{tracked.id}</span>
                  <h3 className="complaints__result-title">{tracked.title}</h3>
                </div>
                <Pill>{tracked.status}</Pill>
              </div>
              <p className="complaints__result-desc">{tracked.description}</p>
              <div className="complaints__result-meta">
                <span>🗂️ {formatCategory(tracked.category)}</span>
                {tracked.location && <span>📍 {tracked.location}</span>}
                {tracked.latitude && (
                  <span>🌐 {tracked.latitude}, {tracked.longitude}</span>
                )}
                <span>🕒 {new Date(tracked.createdAt).toLocaleDateString()}</span>
              </div>
            </GlassCard>
          )}
        </section>
      )}

      {/* TAB 4: REPORT ISSUE */}
      {activeTab === "submit" && (
        <section className="complaints__submit">
          {!isAuthenticated ? (
            <GlassCard className="complaints__login-prompt">
              <h3>Sign in required</h3>
              <p>
                You need an account to submit a complaint. Register in seconds and
                start reporting issues.
              </p>
              <div className="complaints__login-actions">
                <Link to="/login">
                  <Button>Go to Login</Button>
                </Link>
                <Link to="/register">
                  <Button variant="secondary">Create Account</Button>
                </Link>
              </div>
            </GlassCard>
          ) : submitSuccess ? (
            <GlassCard className="complaints__success">
              <div className="complaints__success-icon">✅</div>
              <h3>Complaint submitted successfully!</h3>
              <p>
                Your complaint ID is <strong>#{submitSuccess.id}</strong>. Track
                its progress anytime.
              </p>
              <Button onClick={() => setSubmitSuccess(null)}>
                Submit another
              </Button>
            </GlassCard>
          ) : (
            <GlassCard className="complaints__form-card">
              <form className="complaints__form" onSubmit={handleSubmit}>
                <Input
                  name="title"
                  label="Title"
                  placeholder="Brief title of the issue"
                  value={form.title}
                  onChange={handleChange}
                  required
                />
                <div className="aicip-field">
                  <label className="aicip-field__label" htmlFor="description">
                    Description <span className="aicip-field__required">*</span>
                  </label>
                  <textarea
                    id="description"
                    name="description"
                    className="aicip-field__input complaints__textarea"
                    placeholder="Describe the issue in detail"
                    value={form.description}
                    onChange={handleChange}
                    required
                    rows={4}
                  />
                </div>
                <div className="aicip-field">
                  <label className="aicip-field__label" htmlFor="category">
                    Category <span className="aicip-field__required">*</span>
                  </label>
                  <select
                    id="category"
                    name="category"
                    className="aicip-field__input"
                    value={form.category}
                    onChange={handleChange}
                    required
                  >
                    <option value="">Select a category</option>
                    {CATEGORIES.map((c) => (
                      <option key={c} value={c}>
                        {formatCategory(c)}
                      </option>
                    ))}
                  </select>
                </div>
                <Input
                  name="location"
                  label="Location"
                  placeholder="e.g. Gandhi Road, Villupuram"
                  value={form.location}
                  onChange={handleChange}
                  required
                />

                <div className="complaints__coord-row">
                  <Input
                    name="latitude"
                    label="Latitude (optional)"
                    placeholder="e.g. 13.0827"
                    value={form.latitude}
                    onChange={handleChange}
                  />
                  <Input
                    name="longitude"
                    label="Longitude (optional)"
                    placeholder="e.g. 80.2707"
                    value={form.longitude}
                    onChange={handleChange}
                  />
                </div>

                <div className="aicip-field">
                  <label className="aicip-field__label" htmlFor="image">
                    Photo (optional)
                  </label>
                  <input
                    id="image"
                    name="image"
                    type="file"
                    accept="image/jpeg,image/png"
                    className="aicip-field__input complaints__file"
                    onChange={handleImageChange}
                  />
                  {imagePreview && (
                    <img
                      src={imagePreview}
                      alt="Complaint preview"
                      className="complaints__preview"
                    />
                  )}
                  <p className="aicip-field__helper">JPG or PNG, up to 5 MB.</p>
                </div>

                {submitError && (
                  <p className="complaints__error">{submitError}</p>
                )}

                <Button type="submit" size="lg" loading={submitLoading}>
                  Submit Complaint
                </Button>
              </form>
            </GlassCard>
          )}
        </section>
      )}

      {/* Complaint Detail Modal */}
      {selectedComplaint && (
        <div className="complaints__modal-backdrop" onClick={() => setSelectedComplaint(null)}>
          <GlassCard className="complaints__modal-card" onClick={(e) => e.stopPropagation()}>
            <div className="complaints__modal-head">
              <h3>Complaint #{selectedComplaint.id}</h3>
              <Pill>{selectedComplaint.status}</Pill>
            </div>

            <h4>{selectedComplaint.title}</h4>
            <p className="complaints__modal-desc">{selectedComplaint.description}</p>

            <div className="complaints__modal-meta">
              <div className="meta-row">
                <span className="label">Category:</span>
                <span className="val">{formatCategory(selectedComplaint.category)}</span>
              </div>
              <div className="meta-row">
                <span className="label">Location:</span>
                <span className="val">{selectedComplaint.location}</span>
              </div>
              {selectedComplaint.latitude && (
                <div className="meta-row">
                  <span className="label">Coordinates:</span>
                  <span className="val">
                    {selectedComplaint.latitude}, {selectedComplaint.longitude}
                  </span>
                </div>
              )}
              <div className="meta-row">
                <span className="label">Submitted By:</span>
                <span className="val">{selectedComplaint.citizenName}</span>
              </div>
              <div className="meta-row">
                <span className="label">Created Date:</span>
                <span className="val">{new Date(selectedComplaint.createdAt).toLocaleString()}</span>
              </div>
            </div>

            {selectedComplaint.imageUrl && (
              <div className="complaints__modal-img">
                <img
                  src={`http://localhost:8080${selectedComplaint.imageUrl}`}
                  alt={selectedComplaint.title}
                />
              </div>
            )}

            <div className="complaints__modal-actions">
              <Button size="sm" onClick={() => setSelectedComplaint(null)}>
                Close
              </Button>
            </div>
          </GlassCard>
        </div>
      )}
    </div>
  );
}

export default Complaints;
