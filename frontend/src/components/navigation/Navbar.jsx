import { useState } from "react";
import { Link, NavLink, useNavigate } from "react-router";
import ThemeToggle from "../ui/ThemeToggle.jsx";
import Button from "../ui/Button.jsx";
import { useAuth } from "../../hooks/useAuth.js";
import "./Navbar.css";

const NAV_LINKS = [
  { to: "/", label: "Home" },
  { to: "/complaints", label: "Complaints" },
  { to: "/services", label: "Services" },
  { to: "/about", label: "About" },
];

const ADMIN_LINKS = [
  { to: "/dashboard", label: "Dashboard" },
  { to: "/admin/complaints", label: "Manage Complaints" },
  { to: "/admin/analytics", label: "Analytics" },
];

function Navbar() {
  const [menuOpen, setMenuOpen] = useState(false);
  const { isAuthenticated, isAdmin, logout } = useAuth();
  const navigate = useNavigate();

  const closeMenu = () => setMenuOpen(false);

  const handleLogout = async () => {
    await logout();
    closeMenu();
    navigate("/");
  };

  return (
    <header className="aicip-navbar">
      <div className="aicip-navbar__inner">
        <NavLink to="/" className="aicip-navbar__brand" aria-label="AICIP Home">
          AICIP
        </NavLink>

        <nav
          className={`aicip-navbar__nav ${
            menuOpen ? "aicip-navbar__nav--open" : ""
          }`}
          aria-label="Primary navigation"
        >
          {NAV_LINKS.map((link) => (
            <NavLink
              key={link.to}
              to={link.to}
              end={link.to === "/"}
              className={({ isActive }) =>
                `aicip-navbar__link ${
                  isActive ? "aicip-navbar__link--active" : ""
                }`
              }
              onClick={closeMenu}
            >
              {link.label}
            </NavLink>
          ))}

          {isAdmin &&
            ADMIN_LINKS.map((link) => (
              <NavLink
                key={link.to}
                to={link.to}
                className={({ isActive }) =>
                  `aicip-navbar__link aicip-navbar__link--admin ${
                    isActive ? "aicip-navbar__link--active" : ""
                  }`
                }
                onClick={closeMenu}
              >
                {link.label}
              </NavLink>
            ))}

          <div className="aicip-navbar__mobile-actions">
            {isAuthenticated ? (
              <>
                <NavLink
                  to="/dashboard"
                  className="aicip-navbar__link"
                  onClick={closeMenu}
                >
                  Dashboard
                </NavLink>
                <Button variant="ghost" size="sm" onClick={handleLogout}>
                  Logout
                </Button>
              </>
            ) : (
              <>
                <NavLink
                  to="/login"
                  className="aicip-navbar__link"
                  onClick={closeMenu}
                >
                  Sign In
                </NavLink>
                <Link to="/register" onClick={closeMenu}>
                  <Button size="sm">Get Started</Button>
                </Link>
              </>
            )}
          </div>
        </nav>

        <div className="aicip-navbar__actions">
          {isAuthenticated ? (
            <Link to="/dashboard" className="aicip-navbar__dashboard-link">
              Dashboard
            </Link>
          ) : (
            <>
              <Link to="/login" className="aicip-navbar__login-link">
                Sign In
              </Link>
              <Link to="/register" className="aicip-navbar__cta">
                <Button size="sm">Get Started</Button>
              </Link>
            </>
          )}
          <ThemeToggle />

          <button
            type="button"
            className={`aicip-navbar__hamburger ${
              menuOpen ? "aicip-navbar__hamburger--open" : ""
            }`}
            aria-label={menuOpen ? "Close menu" : "Open menu"}
            aria-expanded={menuOpen}
            onClick={() => setMenuOpen((v) => !v)}
          >
            <span />
            <span />
            <span />
          </button>
        </div>
      </div>
    </header>
  );
}

export default Navbar;
