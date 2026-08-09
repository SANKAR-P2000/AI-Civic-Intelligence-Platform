import ThemeToggle from "./components/ui/ThemeToggle.jsx";
import Navbar from "./components/navigation/Navbar.jsx";
function App() {
  return (
    <div className="app-shell">
      <Navbar />
      <header className="app-header">
        <div className="app-brand">
          <span className="app-brand__title">AICIP</span>
        </div>
        <main className="app-content">{/* Existing UI */}</main>
        <ThemeToggle />
      </header>

      {/* Existing UI content goes here */}
    </div>
  );
}

export default App;
