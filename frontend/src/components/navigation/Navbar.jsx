import { NavLink } from "react-router";
import ThemeToggle from "../ui/ThemeToggle.jsx";
import "./Navbar.css";

function Navbar() {
  return (
    <header className="aicip-navbar">
      <div className="aicip-navbar__inner">
        <NavLink to="/" className="aicip-navbar__brand" aria-label="AICIP Home">
          AICIP
        </NavLink>

        <nav className="aicip-navbar__nav" aria-label="Primary navigation">
          <NavLink
            to="/"
            className={({ isActive }) =>
              `aicip-navbar__link ${
                isActive ? "aicip-navbar__link--active" : ""
              }`
            }
          >
            Home
          </NavLink>

          <NavLink
            to="/complaints"
            className={({ isActive }) =>
              `aicip-navbar__link ${
                isActive ? "aicip-navbar__link--active" : ""
              }`
            }
          >
            Complaints
          </NavLink>

          <NavLink
            to="/services"
            className={({ isActive }) =>
              `aicip-navbar__link ${
                isActive ? "aicip-navbar__link--active" : ""
              }`
            }
          >
            Services
          </NavLink>

          <NavLink
            to="/about"
            className={({ isActive }) =>
              `aicip-navbar__link ${
                isActive ? "aicip-navbar__link--active" : ""
              }`
            }
          >
            About
          </NavLink>
        </nav>

        <div className="aicip-navbar__actions">
          <ThemeToggle />
        </div>
      </div>
    </header>
  );
}

export default Navbar;
