import { useState } from "react";
import { Link, useLocation, useNavigate } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import Input from "../components/ui/Input.jsx";
import { useAuth } from "../hooks/useAuth.js";
import "./Auth.css";

function Login() {
  const { login } = useAuth();
  const navigate = useNavigate();
  const location = useLocation();
  const from = location.state?.from?.pathname || "/dashboard";

  const [form, setForm] = useState({ email: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm((prev) => ({ ...prev, [name]: value }));
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    setError("");

    if (!form.email || !form.password) {
      setError("Please enter both email and password.");
      return;
    }

    setLoading(true);
    try {
      await login(form);
      navigate(from, { replace: true });
    } catch (err) {
      setError(err.message || "Login failed. Please try again.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="auth">
      <GlassCard className="auth__card">
        <div className="auth__brand">
          <h1>AICIP</h1>
          <p>Welcome back! Sign in to continue.</p>
        </div>

        <form className="auth__form" onSubmit={handleSubmit} noValidate>
          {error && (
            <p className="auth__error" role="alert">
              {error}
            </p>
          )}

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
            label="Password"
            name="password"
            type="password"
            placeholder="Enter your password"
            value={form.password}
            onChange={handleChange}
            required
            autoComplete="current-password"
          />

          <Button
            type="submit"
            size="lg"
            loading={loading}
            className="auth__submit"
          >
            Sign In
          </Button>
        </form>

        <p className="auth__footer">
          Don&apos;t have an account?
          <Link to="/register">Create one</Link>
        </p>
      </GlassCard>
    </div>
  );
}

export default Login;
