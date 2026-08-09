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
};

function Complaints() {
  const { isAuthenticated } = useAuth();
  const [searchParams, setSearchParams] = useSearchParams();
  const trackParam = searchParams.get("track");

  // Track state
  const [trackId, setTrackId] = useState(trackParam || "");
  const [tracked, setTracked] = useState(null);
  const [trackLoading, setTrackLoading] = useState(false);
  const [trackError, setTrackError] = useState("");

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
        "Unable to track this complaint. The ID may be invalid or access is restricted.",
      );
    } finally {
      setTrackLoading(false);
    }
  }, []);

  useEffect(() => {
    if (!trackParam) return undefined;
    const timer = setTimeout(() => fetchTrack(trackParam), 0);
    return () => clearTimeout(timer);
  }, [trackParam, fetchTrack]);

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

      // Upload image first if one was selected
      if (imageFile) {
        try {
          imageUrl = await uploadComplaintImage(imageFile);
        } catch (uploadErr) {
          setSubmitError(uploadErr.message || "Image upload failed.");
          setSubmitLoading(false);
          return;
        }
      }

      const data = await complaintService.createComplaint({
        ...form,
        imageUrl,
      });
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
      {/* TRACK SECTION */}
      <section className="complaints__track">
        <SectionHeading
          eyebrow="Track Complaint"
          title="Track the status of your complaint"
          subtitle="Enter the complaint ID you received when submitting to follow its live progress."
        />

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
                <span className="complaints__result-label">Complaint</span>
                <h3 className="complaints__result-title">{tracked.title}</h3>
              </div>
              <Pill>{tracked.status}</Pill>
            </div>
            <p className="complaints__result-desc">{tracked.description}</p>
            <div className="complaints__result-meta">
              <span>🗂️ {formatCategory(tracked.category)}</span>
              {tracked.location && <span>📍 {tracked.location}</span>}
              <span>🕒 {new Date(tracked.createdAt).toLocaleDateString()}</span>
            </div>
          </GlassCard>
        )}
      </section>

      {/* SUBMIT SECTION */}
      <section className="complaints__submit">
        <SectionHeading
          eyebrow="Report an Issue"
          title="Submit a new complaint"
          subtitle="Help us keep your community safe and well-maintained."
        />

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
    </div>
  );
}

export default Complaints;
