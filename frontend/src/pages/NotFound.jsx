import { Link } from "react-router";
import GlassCard from "../components/ui/GlassCard.jsx";
import Button from "../components/ui/Button.jsx";
import "./NotFound.css";

function NotFound() {
  return (
    <div className="notfound">
      <GlassCard className="notfound__card">
        <div className="notfound__code">404</div>
        <h1 className="notfound__title">Page not found</h1>
        <p className="notfound__desc">
          The page you're looking for doesn't exist or has been moved.
        </p>
        <Link to="/">
          <Button size="lg">Back to Home</Button>
        </Link>
      </GlassCard>
    </div>
  );
}

export default NotFound;
