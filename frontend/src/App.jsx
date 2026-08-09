import GlassCard from "./components/ui/GlassCard";

function App() {
  return (
    <main className="app-shell">
      <section className="app-content">
        <GlassCard>
          <span className="text-kpi">1,248</span>

          <p className="text-kpi-label">Total Complaints</p>

          <p className="text-body-secondary">
            Civic complaints received across the platform.
          </p>
        </GlassCard>
      </section>
    </main>
  );
}

export default App;
