import { useState } from "react";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import ThemeToggle from "../components/ui/ThemeToggle.jsx";
import { useAuth } from "../hooks/useAuth.js";
import { Link } from "react-router";
import "./Settings.css";

function Settings() {
  const { user, changePassword, verifyEmail, refreshUser } = useAuth();

  // Change Password State
  const [currentPassword, setCurrentPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");
  const [passwordLoading, setPasswordLoading] = useState(false);
  const [passwordError, setPasswordError] = useState("");
  const [passwordSuccess, setPasswordSuccess] = useState("");

  const handleChangePassword = async (e) => {
    e.preventDefault();
    if (newPassword !== confirmPassword) {
      setPasswordError("New password and confirm password do not match.");
      return;
    }
    try {
      setPasswordLoading(true);
      setPasswordError("");
      setPasswordSuccess("");

      await changePassword({
        currentPassword,
        newPassword,
        confirmPassword,
      });

      setPasswordSuccess("Password changed successfully!");
      setCurrentPassword("");
      setNewPassword("");
      setConfirmPassword("");
    } catch (err) {
      setPasswordError(
        err.response?.data?.message || "Failed to change password. Please check your current password."
      );
    } finally {
      setPasswordLoading(false);
    }
  };

  return (
    <div className="aicip-settings-page">
      <div className="aicip-settings-container">
        <h1 className="aicip-settings-title">Account Settings</h1>

        {/* SECTION 1: ACCOUNT */}
        <GlassCard className="aicip-settings-section">
          <div className="aicip-settings-header">
            <span className="aicip-section-tag">ACCOUNT</span>
            <h2>Profile & Verification</h2>
          </div>

          <div className="aicip-settings-content">
            <div className="aicip-setting-row">
              <div>
                <span className="setting-label">Account Overview</span>
                <span className="setting-desc">
                  Manage your personal details, name, avatar, and contact numbers.
                </span>
              </div>
              <Link to="/profile">
                <Button variant="secondary" size="sm">
                  View Profile
                </Button>
              </Link>
            </div>

            <div className="aicip-setting-row">
              <div>
                <span className="setting-label">Email Verification</span>
                <span className="setting-desc">{user?.email}</span>
              </div>
              <span
                className={`aicip-badge ${
                  user?.emailVerified ? "verified" : "unverified"
                }`}
              >
                {user?.emailVerified ? "Verified ✓" : "Unverified"}
              </span>
            </div>

            <div className="aicip-setting-row">
              <div>
                <span className="setting-label">Phone Verification</span>
                <span className="setting-desc">{user?.phoneNumber || "Not specified"}</span>
              </div>
              <span
                className={`aicip-badge ${
                  user?.phoneVerified ? "verified" : "unverified"
                }`}
              >
                {user?.phoneVerified ? "Verified ✓" : "Unverified"}
              </span>
            </div>
          </div>
        </GlassCard>

        {/* SECTION 2: SECURITY */}
        <GlassCard className="aicip-settings-section">
          <div className="aicip-settings-header">
            <span className="aicip-section-tag">SECURITY</span>
            <h2>Change Password</h2>
          </div>

          <div className="aicip-settings-content">
            {passwordError && (
              <div className="aicip-settings-alert error">{passwordError}</div>
            )}
            {passwordSuccess && (
              <div className="aicip-settings-alert success">{passwordSuccess}</div>
            )}

            <form onSubmit={handleChangePassword} className="aicip-password-form">
              <Input
                label="Current Password"
                type="password"
                value={currentPassword}
                onChange={(e) => setCurrentPassword(e.target.value)}
                placeholder="••••••••"
                required
              />
              <Input
                label="New Password"
                type="password"
                value={newPassword}
                onChange={(e) => setNewPassword(e.target.value)}
                placeholder="At least 8 characters"
                required
              />
              <Input
                label="Confirm New Password"
                type="password"
                value={confirmPassword}
                onChange={(e) => setConfirmPassword(e.target.value)}
                placeholder="Re-enter new password"
                required
              />

              <div className="aicip-form-actions">
                <Button type="submit" loading={passwordLoading} size="sm">
                  Update Password
                </Button>
              </div>
            </form>
          </div>
        </GlassCard>

        {/* SECTION 3: APPEARANCE */}
        <GlassCard className="aicip-settings-section">
          <div className="aicip-settings-header">
            <span className="aicip-section-tag">APPEARANCE</span>
            <h2>Theme Preferences</h2>
          </div>

          <div className="aicip-settings-content">
            <div className="aicip-setting-row">
              <div>
                <span className="setting-label">Display Mode</span>
                <span className="setting-desc">
                  Switch between Daylight Mode and Night Mode for optimal visual comfort.
                </span>
              </div>
              <ThemeToggle />
            </div>
          </div>
        </GlassCard>

        {/* SECTION 4: NOTIFICATIONS */}
        <GlassCard className="aicip-settings-section">
          <div className="aicip-settings-header">
            <span className="aicip-section-tag">NOTIFICATIONS</span>
            <h2>Notification Preferences</h2>
          </div>

          <div className="aicip-settings-content">
            <div className="aicip-setting-row disabled">
              <div>
                <span className="setting-label">Email Alerts & System Updates</span>
                <span className="setting-desc">
                  Receive email updates on complaint status updates and announcements.
                </span>
              </div>
              <span className="aicip-badge coming-soon">Coming Soon</span>
            </div>
          </div>
        </GlassCard>
      </div>
    </div>
  );
}

export default Settings;
