import { useState } from "react";
import { Link, useNavigate } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import { useAuth } from "../hooks/useAuth.js";
import "./Auth.css";

function Register() {
  const { register, login } = useAuth();
  const navigate = useNavigate();

  const [form, setForm] = useState({
    fullName: "",
    email: "",
    phoneNumber: "",
    password: "",
    confirmPassword: "",
  });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    // Client-side validation
    if (!form.fullName || !form.email || !form.phoneNumber || !form.password) {
      setError("Please fill in all required fields.");
      return;
    }
    if (!/^[0-9]{10}$/.test(form.phoneNumber)) {
      setError("Phone number must be exactly 10 digits.");
      return;
    }
    if (form.password.length < 8) {
      setError("Password must be at least 8 characters long.");
      return;
    }
    if (form.password !== form.confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    setLoading(true);
    try {
      await register({
        fullName: form.fullName,
        email: form.email,
        phoneNumber: form.phoneNumber,
        password: form.password,
      });
      // Auto-login after successful registration
      await login({ email: form.email, password: form.password });
      navigate("/dashboard", { replace: true });
    } catch (err) {
      setError(err.message || "Registration failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth">
      <GlassCard className="auth__card">
        <div className="auth__brand">
          <h1>AICIP</h1>
          <p>Create your account and start making a difference.</p>
        </div>

        <form className="auth__form" onSubmit={handleSubmit} noValidate>
          {error && (
            <p className="auth__error" role="alert">
              {error}
            </p>
          )}

          <Input
            label="Full Name"
            name="fullName"
            placeholder="e.g. Sankar P"
            value={form.fullName}
            onChange={handleChange}
            required
            autoComplete="name"
          />

          <Input
            label="Email Address"
            name="email"
            type="email"
            placeholder="you@example.com"
            value={form.email}
            onChange={handleChange}
            required
            autoComplete="email"
          />

          <Input
            label="Phone Number"
            name="phoneNumber"
            type="tel"
            placeholder="10-digit mobile number"
            value={form.phoneNumber}
            onChange={handleChange}
            required
            autoComplete="tel"
            maxLength={10}
          />

          <Input
            label="Password"
            name="password"
            type="password"
            placeholder="At least 8 characters"
            value={form.password}
            onChange={handleChange}
            required
            autoComplete="new-password"
          />

          <Input
            label="Confirm Password"
            name="confirmPassword"
            type="password"
            placeholder="Re-enter your password"
            value={form.confirmPassword}
            onChange={handleChange}
            required
            autoComplete="new-password"
          />

          <Button
            type="submit"
            size="lg"
            loading={loading}
            className="auth__submit"
          >
            Create Account
          </Button>
        </form>

        <p className="auth__footer">
          Already have an account?
          <Link to="/login">Sign in</Link>
        </p>
      </GlassCard>
    </div>
  );
}

export default Register;
