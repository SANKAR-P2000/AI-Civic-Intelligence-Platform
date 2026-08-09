import { Link } from "react-router";
import { useAuth } from "../../hooks/useAuth.js";
import "./Footer.css";

function Footer() {
  const { isAuthenticated, isAdmin } = useAuth();
  const year = new Date().getFullYear();

  return (
    <footer className="aicip-footer">
      <div className="aicip-footer__inner">
        <div className="aicip-footer__brand">
          <div className="aicip-footer__logo">AICIP</div>
          <p className="aicip-footer__tagline">
            AI Civic Intelligence Platform — empowering citizens to shape
            smarter, safer communities.
          </p>
        </div>

        <nav className="aicip-footer__col" aria-label="Footer platform links">
          <h4 className="aicip-footer__heading">Platform</h4>
          <Link to="/complaints">Report a Complaint</Link>
          <Link to="/complaints">Track Complaint</Link>
          <Link to="/services">Services</Link>
          {isAuthenticated && <Link to="/dashboard">Dashboard</Link>}
          {isAuthenticated && (
            <Link to={isAdmin ? "/admin/complaints" : "/dashboard"}>
              My {isAdmin ? "Admin" : "Dashboard"}
            </Link>
          )}
        </nav>

        <nav className="aicip-footer__col" aria-label="Footer company links">
          <h4 className="aicip-footer__heading">Company</h4>
          <Link to="/about">About Us</Link>
          <Link to="/about">Contact</Link>
          <Link to="/about">Our Mission</Link>
        </nav>

        <nav className="aicip-footer__col" aria-label="Footer legal links">
          <h4 className="aicip-footer__heading">Legal</h4>
          <Link to="/about">Privacy Policy</Link>
          <Link to="/about">Terms of Service</Link>
          <Link to="/about">Accessibility</Link>
        </nav>
      </div>

      <div className="aicip-footer__bottom">
        <p>© {year} AICIP. All rights reserved.</p>
        <p>Designed for civic progress.</p>
      </div>
    </footer>
  );
}

export default Footer;
