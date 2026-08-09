function MainLayout({ children }) {
  return (
    <div className="app-layout">
      <header>
        <h1>AICIP</h1>
      </header>

      <main>{children}</main>

      <footer>
        <p>AI Civic Intelligence Platform</p>
      </footer>
    </div>
  );
}

export default MainLayout;
