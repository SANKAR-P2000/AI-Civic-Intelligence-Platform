import GlassCard from "./components/ui/GlassCard";
import Input from "./components/ui/Input";

function App() {
  return (
    <main className="app-shell">
      <section className="app-content">
        <GlassCard>
          <div className="form-demo">
            <Input
              label="Full Name"
              name="fullName"
              placeholder="Enter your full name"
              required
            />

            <Input
              label="Email Address"
              name="email"
              type="email"
              placeholder="Enter your email"
              helperText="Use the email address associated with your account."
              required
            />

            <Input
              label="Password"
              name="password"
              type="password"
              placeholder="Enter your password"
              required
            />

            <Input
              label="Invalid Email"
              name="invalidEmail"
              type="email"
              placeholder="Enter your email"
              error="Please enter a valid email address."
            />

            <Input
              label="Disabled Field"
              name="disabled"
              placeholder="This field is disabled"
              disabled
            />
          </div>
        </GlassCard>
      </section>
    </main>
  );
}

export default App;
