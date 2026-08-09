import { Outlet } from "react-router";
import Navbar from "../components/navigation/Navbar.jsx";
import Footer from "../components/layout/Footer.jsx";

// Main application layout — wraps Navbar, routed content, and Footer.
function MainLayout() {
  return (
    <div className="app-layout">
      <Navbar />
      <main className="app-content">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

export default MainLayout;
