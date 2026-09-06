import { useState, useEffect } from "react";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import { useAuth } from "../hooks/useAuth.js";
import authService from "../services/auth.js";
import "./Profile.css";

function Profile() {
  const { user, refreshUser, updateProfile, uploadProfilePicture, verifyEmail } = useAuth();

  const [profileData, setProfileData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState("");
  const [successMessage, setSuccessMessage] = useState("");

  // Edit profile state
  const [isEditing, setIsEditing] = useState(false);
  const [editName, setEditName] = useState("");
  const [editPhone, setEditPhone] = useState("");
  const [updateLoading, setUpdateLoading] = useState(false);

  // Profile picture upload state
  const [uploadingPic, setUploadingPic] = useState(false);
  const [imagePreview, setImagePreview] = useState(null);

  // Email OTP verification state
  const [showOtpModal, setShowOtpModal] = useState(false);
  const [otpCode, setOtpCode] = useState("");
  const [otpLoading, setOtpLoading] = useState(false);
  const [otpError, setOtpError] = useState("");
  const [otpMessage, setOtpMessage] = useState("");
  const [resendCooldown, setResendCooldown] = useState(0);

  useEffect(() => {
    let timer;
    if (resendCooldown > 0) {
      timer = setInterval(() => {
        setResendCooldown((prev) => prev - 1);
      }, 1000);
    }
    return () => clearInterval(timer);
  }, [resendCooldown]);

  const loadProfile = async () => {
    try {
      setLoading(true);
      setError("");
      const res = await authService.getProfile();
      setProfileData(res);
      setEditName(res.fullName || "");
      setEditPhone(res.phoneNumber || "");
    } catch (err) {
      setError(err.response?.data?.message || "Failed to load user profile.");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadProfile();
  }, []);

  const handleUpdateProfile = async (e) => {
    e.preventDefault();
    try {
      setUpdateLoading(true);
      setError("");
      setSuccessMessage("");
      const updated = await updateProfile({
        fullName: editName,
        phoneNumber: editPhone,
      });
      setProfileData(updated);
      setIsEditing(false);
      setSuccessMessage("Profile updated successfully!");
    } catch (err) {
      setError(err.response?.data?.message || "Failed to update profile.");
    } finally {
      setUpdateLoading(false);
    }
  };

  const handlePictureChange = async (e) => {
    const file = e.target.files?.[0];
    if (!file) return;

    // Show preview
    const previewUrl = URL.createObjectURL(file);
    setImagePreview(previewUrl);

    try {
      setUploadingPic(true);
      setError("");
      setSuccessMessage("");
      const updated = await uploadProfilePicture(file);
      setProfileData(updated);
      setSuccessMessage("Profile picture updated successfully!");
    } catch (err) {
      setError(err.response?.data?.message || "Failed to upload profile picture.");
      setImagePreview(null);
    } finally {
      setUploadingPic(false);
    }
  };

  const handleInitiateEmailVerify = async () => {
    if (!profileData?.email) return;
    try {
      setOtpError("");
      setOtpMessage("");
      setShowOtpModal(true);
      await authService.sendVerificationOtp(profileData.email);
      setOtpMessage("Verification OTP sent to your email.");
      setResendCooldown(60);
    } catch (err) {
      setOtpError(err.response?.data?.message || "Failed to send verification OTP.");
    }
  };

  const handleResendOtp = async () => {
    if (resendCooldown > 0 || !profileData?.email) return;
    try {
      setOtpError("");
      setOtpMessage("");
      await authService.resendEmailOtp(profileData.email);
      setOtpMessage("A new verification OTP has been sent.");
      setResendCooldown(60);
    } catch (err) {
      setOtpError(err.response?.data?.message || "Failed to resend OTP.");
    }
  };

  const handleVerifyOtp = async (e) => {
    e.preventDefault();
    if (!otpCode || otpCode.length !== 6) {
      setOtpError("Please enter a valid 6-digit OTP.");
      return;
    }
    try {
      setOtpLoading(true);
      setOtpError("");
      await verifyEmail(profileData.email, otpCode);
      setShowOtpModal(false);
      setOtpCode("");
      setSuccessMessage("Email verified successfully!");
      await loadProfile();
      await refreshUser();
    } catch (err) {
      setOtpError(err.response?.data?.message || "Invalid or expired OTP code.");
    } finally {
      setOtpLoading(false);
    }
  };

  if (loading) {
    return (
      <div className="aicip-profile-page">
        <GlassCard className="aicip-profile-card">
          <div className="aicip-profile-skeleton">Loading user profile...</div>
        </GlassCard>
      </div>
    );
  }

  const avatarSrc =
    imagePreview ||
    (profileData?.profilePictureUrl
      ? `http://localhost:8080${profileData.profilePictureUrl}`
      : null);

  const formattedDate = profileData?.createdAt
    ? new Date(profileData.createdAt).toLocaleDateString("en-US", {
        year: "numeric",
        month: "long",
        day: "numeric",
      })
    : "N/A";

  return (
    <div className="aicip-profile-page">
      <div className="aicip-profile-container">
        {error && <div className="aicip-profile-alert error">{error}</div>}
        {successMessage && <div className="aicip-profile-alert success">{successMessage}</div>}

        <GlassCard className="aicip-profile-header-card">
          <div className="aicip-profile-header-content">
            <div className="aicip-avatar-wrapper">
              <div className="aicip-avatar">
                {avatarSrc ? (
                  <img src={avatarSrc} alt={profileData?.fullName || "User"} />
                ) : (
                  <div className="aicip-avatar-placeholder">
                    {(profileData?.fullName || user?.fullName || "U").charAt(0).toUpperCase()}
                  </div>
                )}
                {uploadingPic && <div className="aicip-avatar-spinner">...</div>}
              </div>
              <label htmlFor="avatar-upload" className="aicip-avatar-upload-btn">
                📷 Change
                <input
                  id="avatar-upload"
                  type="file"
                  accept="image/*"
                  onChange={handlePictureChange}
                  disabled={uploadingPic}
                  hidden
                />
              </label>
            </div>

            <div className="aicip-profile-header-details">
              <h1 className="aicip-profile-name">{profileData?.fullName}</h1>
              <p className="aicip-profile-role">{profileData?.role}</p>

              <div className="aicip-profile-badges">
                <span
                  className={`aicip-badge ${
                    profileData?.emailVerified ? "verified" : "unverified"
                  }`}
                >
                  {profileData?.emailVerified ? "Email Verified ✓" : "Email Unverified"}
                </span>

                <span
                  className={`aicip-badge ${
                    profileData?.phoneVerified ? "verified" : "unverified"
                  }`}
                >
                  {profileData?.phoneVerified ? "Phone Verified ✓" : "Phone Unverified"}
                </span>
              </div>
            </div>
          </div>
        </GlassCard>

        {/* Profile Information & Edit Card */}
        <GlassCard className="aicip-profile-details-card">
          <div className="aicip-card-header">
            <h2>Account Details</h2>
            {!isEditing && (
              <Button size="sm" variant="secondary" onClick={() => setIsEditing(true)}>
                Edit Profile
              </Button>
            )}
          </div>

          {isEditing ? (
            <form onSubmit={handleUpdateProfile} className="aicip-profile-form">
              <Input
                label="Full Name"
                value={editName}
                onChange={(e) => setEditName(e.target.value)}
                required
              />
              <Input
                label="Phone Number"
                value={editPhone}
                onChange={(e) => setEditPhone(e.target.value)}
                required
              />
              <div className="aicip-form-actions">
                <Button type="submit" loading={updateLoading} size="sm">
                  Save Changes
                </Button>
                <Button
                  type="button"
                  variant="ghost"
                  size="sm"
                  onClick={() => {
                    setIsEditing(false);
                    setEditName(profileData?.fullName || "");
                    setEditPhone(profileData?.phoneNumber || "");
                  }}
                >
                  Cancel
                </Button>
              </div>
            </form>
          ) : (
            <div className="aicip-details-grid">
              <div className="aicip-detail-item">
                <span className="label">Full Name</span>
                <span className="value">{profileData?.fullName}</span>
              </div>

              <div className="aicip-detail-item">
                <span className="label">Email Address</span>
                <div className="value-with-action">
                  <span className="value">{profileData?.email}</span>
                  {!profileData?.emailVerified && (
                    <Button size="xs" variant="secondary" onClick={handleInitiateEmailVerify}>
                      Verify Email
                    </Button>
                  )}
                </div>
              </div>

              <div className="aicip-detail-item">
                <span className="label">Phone Number</span>
                <span className="value">{profileData?.phoneNumber}</span>
              </div>

              <div className="aicip-detail-item">
                <span className="label">Role</span>
                <span className="value">{profileData?.role}</span>
              </div>

              <div className="aicip-detail-item">
                <span className="label">Member Since</span>
                <span className="value">{formattedDate}</span>
              </div>
            </div>
          )}
        </GlassCard>
      </div>

      {/* OTP Verification Modal */}
      {showOtpModal && (
        <div className="aicip-modal-backdrop">
          <GlassCard className="aicip-modal-card">
            <h3>Verify Your Email Address</h3>
            <p className="aicip-modal-desc">
              We have sent a 6-digit OTP code to <strong>{profileData?.email}</strong>.
            </p>

            {otpMessage && <div className="aicip-profile-alert success">{otpMessage}</div>}
            {otpError && <div className="aicip-profile-alert error">{otpError}</div>}

            <form onSubmit={handleVerifyOtp} className="aicip-otp-form">
              <Input
                label="Enter 6-Digit OTP Code"
                value={otpCode}
                onChange={(e) => setOtpCode(e.target.value)}
                maxLength={6}
                placeholder="123456"
                required
              />

              <div className="aicip-modal-actions">
                <Button type="submit" loading={otpLoading}>
                  Verify OTP
                </Button>
                <Button
                  type="button"
                  variant="ghost"
                  disabled={resendCooldown > 0}
                  onClick={handleResendOtp}
                >
                  {resendCooldown > 0 ? `Resend in ${resendCooldown}s` : "Resend OTP"}
                </Button>
                <Button
                  type="button"
                  variant="secondary"
                  onClick={() => setShowOtpModal(false)}
                >
                  Cancel
                </Button>
              </div>
            </form>
          </GlassCard>
        </div>
      )}
    </div>
  );
}

export default Profile;
