import { Navigate, useLocation } from "react-router";
import { useAuth } from "../hooks/useAuth.js";
import { Spinner } from "../components/ui/Spinner.jsx";

function ProtectedRoute({ children, roles }) {
  const { user, loading, isAuthenticated } = useAuth();
  const location = useLocation();

  if (loading) {
    return (
      <div className="route-loading">
        <Spinner size="lg" />
        <p>Loading your session...</p>
      </div>
    );
  }

  if (!isAuthenticated) {
    return <Navigate to="/login" replace state={{ from: location }} />;
  }

  // Role-based access control
  if (roles && !roles.includes(user?.role)) {
    return <Navigate to="/dashboard" replace />;
  }

  return children;
}

export default ProtectedRoute;
