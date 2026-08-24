import { useState, useEffect } from "react";
import { Link, useNavigate } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import authService from "../services/auth.js";
import "./Auth.css";

/* Step indicator component */
function StepIndicator({ current, total }) {
  return (
    <div className="auth__steps" aria-label={`Step ${current} of ${total}`}>
      {Array.from({ length: total }, (_, i) => {
        const step = i + 1;
        const isDone = step < current;
        const isActive = step === current;
        return (
          <div key={step} style={{ display: "flex", alignItems: "center" }}>
            <span
              className={[
                "auth__step-dot",
                isDone ? "auth__step-dot--done" : "",
                isActive ? "auth__step-dot--active" : "",
              ]
                .filter(Boolean)
                .join(" ")}
            />
            {step < total && <span className="auth__step-line" />}
          </div>
        );
      })}
    </div>
  );
}

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

  /* Countdown timer for Resend OTP */
  useEffect(() => {
    if (step !== 2 || resendCooldown <= 0) return;
    const interval = setInterval(() => {
      setResendCooldown((prev) => prev - 1);
    }, 1000);
    return () => clearInterval(interval);
  }, [step, resendCooldown]);

  const clearMessages = () => {
    setError("");
    setMessage("");
  };

  /* ── Step 1: Request OTP ─────────────────────── */
  const handleRequestOtp = async (e) => {
    e.preventDefault();
    clearMessages();
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

  /* ── Step 2: Verify OTP ──────────────────────── */
  const handleVerifyOtp = async (e) => {
    e.preventDefault();
    clearMessages();
    if (!otp || otp.length !== 6) {
      setError("Please enter the 6-digit OTP code.");
      return;
    }
    setLoading(true);
    try {
      const res = await authService.verifyOtp(email, otp);
      setResetToken(res.resetToken);
      setMessage("OTP verified! Please set your new password.");
      setStep(3);
    } catch (err) {
      setError(err.message || "Invalid or expired OTP. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  /* ── Resend OTP ──────────────────────────────── */
  const handleResendOtp = async () => {
    if (resendCooldown > 0 || loading) return;
    clearMessages();
    setLoading(true);
    try {
      const res = await authService.resendOtp(email);
      setMessage(res.message || "A new OTP has been sent to your email.");
      setResendCooldown(60);
    } catch (err) {
      setError(err.message || "Failed to resend OTP. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  /* ── Step 3: Reset Password ──────────────────── */
  const handleResetPassword = async (e) => {
    e.preventDefault();
    clearMessages();
    if (!newPassword || !confirmPassword) {
      setError("Please fill in all fields.");
      return;
    }
    if (newPassword.length < 8) {
      setError("Password must be at least 8 characters long.");
      return;
    }
    if (newPassword !== confirmPassword) {
      setError("Passwords do not match.");
      return;
    }
    setLoading(true);
    try {
      await authService.resetPassword(email, resetToken, newPassword, confirmPassword);
      setStep(4);
    } catch (err) {
      setError(err.message || "Failed to reset password. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  const TOTAL_STEPS = 3;

  return (
    <div className="auth">
      <GlassCard className="auth__card">
        {/* Brand */}
        <div className="auth__brand">
          <h1>AICIP</h1>
          <p>
            {step === 1 && "Enter your email to receive a reset code."}
            {step === 2 && "Enter the OTP code sent to your email."}
            {step === 3 && "Choose your new password."}
            {step === 4 && "Password changed successfully!"}
          </p>
        </div>

        {/* Step indicator — only for steps 1–3 */}
        {step <= TOTAL_STEPS && (
          <StepIndicator current={step} total={TOTAL_STEPS} />
        )}

        {/* Alerts */}
        {error && (
          <p className="auth__error" role="alert">
            ⚠ {error}
          </p>
        )}
        {message && (
          <p className="auth__success" role="status">
            ✓ {message}
          </p>
        )}

        {/* ── Step 1: Email input ──────────────────── */}
        {step === 1 && (
          <form className="auth__form" onSubmit={handleRequestOtp} noValidate>
            <Input
              label="Email Address"
              id="fp-email"
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

        {/* ── Step 2: OTP input ───────────────────── */}
        {step === 2 && (
          <form className="auth__form" onSubmit={handleVerifyOtp} noValidate>
            <p className="auth__hint">
              We sent a 6-digit verification code to <b>{email}</b>. The code
              expires in 10 minutes.
            </p>

            <Input
              label="OTP Verification Code"
              id="fp-otp"
              name="otp"
              type="text"
              inputMode="numeric"
              placeholder="Enter 6-digit code"
              value={otp}
              onChange={(e) =>
                setOtp(e.target.value.replace(/\D/g, "").slice(0, 6))
              }
              required
              maxLength={6}
              autoComplete="one-time-code"
            />

            <Button
              type="submit"
              size="lg"
              loading={loading}
              className="auth__submit"
            >
              Verify OTP
            </Button>

            {/* Resend OTP button with animated cooldown */}
            <div className="auth__resend-row">
              <button
                type="button"
                id="resend-otp-btn"
                onClick={handleResendOtp}
                disabled={resendCooldown > 0 || loading}
                className="auth__resend-btn"
                aria-label={
                  resendCooldown > 0
                    ? `Resend OTP available in ${resendCooldown} seconds`
                    : "Resend OTP"
                }
              >
                {resendCooldown > 0 ? (
                  <>
                    Resend OTP&nbsp;
                    <span className="auth__resend-timer">{resendCooldown}s</span>
                  </>
                ) : (
                  "Resend OTP"
                )}
              </button>
            </div>
          </form>
        )}

        {/* ── Step 3: New password ─────────────────── */}
        {step === 3 && (
          <form className="auth__form" onSubmit={handleResetPassword} noValidate>
            <Input
              label="New Password"
              id="fp-new-password"
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
              id="fp-confirm-password"
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

        {/* ── Step 4: Success ──────────────────────── */}
        {step === 4 && (
          <div className="auth__success-block">
            <div className="auth__success-icon" aria-hidden="true">
              ✓
            </div>
            <p className="auth__success-text">
              Your password has been successfully reset. You can now sign in
              with your new password.
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
