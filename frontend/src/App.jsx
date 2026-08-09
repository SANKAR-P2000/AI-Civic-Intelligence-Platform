import { Outlet } from "react-router";
import Navbar from "./components/navigation/Navbar.jsx";
import Footer from "./components/layout/Footer.jsx";
import "./App.css";

function App() {
  return (
    <div className="app-shell">
      <Navbar />
      <main className="app-content">
        <Outlet />
      </main>
      <Footer />
    </div>
  );
}

export default App;
