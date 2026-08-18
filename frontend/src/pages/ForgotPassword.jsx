import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import authService from "../services/auth.js";
import "./Auth.css";

function ForgotPassword() {
  const navigate = useNavigate();

  const [step, setStep] = useState(1);
  const [email, setEmail] = useState("");
  const [otp, setOtp] = useState("");
  const [resetToken, setResetToken] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [error, setError] = useState("");
  const [message, setMessage] = useState("");
  const [loading, setLoading] = useState(false);
  const [resendCooldown, setResendCooldown] = useState(60);

  // Timer for resend cooldown
  useEffect(() => {
    let interval = null;
    if (step === 2 && resendCooldown > 0) {
      interval = setInterval(() => {
        setResendCooldown((prev) => prev - 1);
      }, 1000);
    } else {
      clearInterval(interval);
    }
    return () => clearInterval(interval);
  }, [step, resendCooldown]);

  const handleRequestOtp = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    if (!email) {
      setError("Please enter your email address.");
      return;
    }

    setLoading(true);
    try {
      const res = await authService.forgotPassword(email);
      setMessage(res.message || "OTP has been sent to your email.");
      setResendCooldown(60);
      setStep(2);
    } catch (err) {
      setError(err.message || "Failed to request OTP. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleVerifyOtp = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    if (!otp) {
      setError("Please enter the 6-digit OTP code.");
      return;
    }

    setLoading(true);
    try {
      const res = await authService.verifyOtp(email, otp);
      setResetToken(res.resetToken);
      setMessage("OTP verified successfully. Please enter your new password.");
      setStep(3);
    } catch (err) {
      setError(err.message || "Invalid or expired OTP. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleResendOtp = async () => {
    if (resendCooldown > 0) return;
    setError("");
    setMessage("");
    setLoading(true);

    try {
      const res = await authService.resendOtp(email);
      setMessage(res.message || "A new OTP has been sent.");
      setResendCooldown(60);
    } catch (err) {
      setError(err.message || "Failed to resend OTP. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const handleResetPassword = async (e) => {
    e.preventDefault();
    setError("");
    setMessage("");

    if (!newPassword || !confirmPassword) {
      setError("Please fill in all fields.");
      return;
    }

    if (newPassword !== confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    if (newPassword.length < 8) {
      setError("Password must be at least 8 characters long.");
      return;
    }

    setLoading(true);
    try {
      await authService.resetPassword(email, resetToken, newPassword, confirmPassword);
      setMessage("Password reset successful!");
      setStep(4);
    } catch (err) {
      setError(err.message || "Failed to reset password. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth">
      <GlassCard className="auth__card">
        <div className="auth__brand">
          <h1>AICIP</h1>
          <p>Reset your account password.</p>
        </div>

        {error && (
          <p className="auth__error" role="alert">
            {error}
          </p>
        )}

        {message && (
          <p className="auth__success" role="status">
            {message}
          </p>
        )}

        {step === 1 && (
          <form className="auth__form" onSubmit={handleRequestOtp} noValidate>
            <Input
              label="Email Address"
              name="email"
              type="email"
              placeholder="you@example.com"
              value={email}
              onChange={(e) => setEmail(e.target.value)}
              required
              autoComplete="email"
            />
            <Button
              type="submit"
              size="lg"
              loading={loading}
              className="auth__submit"
            >
              Send OTP
            </Button>
            <p className="auth__footer">
              Remembered your password?
              <Link to="/login">Sign In</Link>
            </p>
          </form>
        )}

        {step === 2 && (
          <form className="auth__form" onSubmit={handleVerifyOtp} noValidate>
            <p className="auth__hint">
              We have sent a verification code to <b>{email}</b>.
            </p>
            <Input
              label="OTP Verification Code"
              name="otp"
              type="text"
              placeholder="Enter 6-digit code"
              value={otp}
              onChange={(e) => setOtp(e.target.value)}
              required
              maxLength={6}
              autoComplete="off"
            />
            <Button
              type="submit"
              size="lg"
              loading={loading}
              className="auth__submit"
            >
              Verify OTP
            </Button>

            <div className="auth__resend-row">
              <button
                type="button"
                onClick={handleResendOtp}
                disabled={resendCooldown > 0 || loading}
                className="auth__resend-btn"
              >
                {resendCooldown > 0 ? `Resend OTP (${resendCooldown}s)` : "Resend OTP"}
              </button>
            </div>
          </form>
        )}

        {step === 3 && (
          <form className="auth__form" onSubmit={handleResetPassword} noValidate>
            <Input
              label="New Password"
              name="newPassword"
              type="password"
              placeholder="Min 8 characters"
              value={newPassword}
              onChange={(e) => setNewPassword(e.target.value)}
              required
              autoComplete="new-password"
            />
            <Input
              label="Confirm New Password"
              name="confirmPassword"
              type="password"
              placeholder="Confirm your password"
              value={confirmPassword}
              onChange={(e) => setConfirmPassword(e.target.value)}
              required
              autoComplete="new-password"
            />
            <Button
              type="submit"
              size="lg"
              loading={loading}
              className="auth__submit"
            >
              Reset Password
            </Button>
          </form>
        )}

        {step === 4 && (
          <div className="auth__success-block">
            <p className="auth__success-text">
              Your password has been successfully reset.
            </p>
            <Button
              onClick={() => navigate("/login")}
              size="lg"
              className="auth__submit auth__submit--full"
            >
              Back to Login
            </Button>
          </div>
        )}
      </GlassCard>
    </div>
  );
}

export default ForgotPassword;
