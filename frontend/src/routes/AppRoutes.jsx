import { createBrowserRouter } from "react-router";

import App from "../App.jsx";
import ProtectedRoute from "../routes/ProtectedRoute.jsx";
import Home from "../pages/Home.jsx";
import Complaints from "../pages/Complaints.jsx";
import Services from "../pages/Services.jsx";
import About from "../pages/About.jsx";
import Login from "../pages/Login.jsx";
import Register from "../pages/Register.jsx";
import ForgotPassword from "../pages/ForgotPassword.jsx";
import Dashboard from "../pages/Dashboard.jsx";
import AdminComplaints from "../pages/AdminComplaints.jsx";
import AdminAnalytics from "../pages/AdminAnalytics.jsx";
import NotFound from "../pages/NotFound.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    Component: App,
    children: [
      { index: true, Component: Home },
      { path: "complaints", Component: Complaints },
      { path: "services", Component: Services },
      { path: "about", Component: About },
      { path: "login", Component: Login },
      { path: "register", Component: Register },
      { path: "forgot-password", Component: ForgotPassword },
      {
        path: "dashboard",
        Component: () => (
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        ),
      },
      {
        path: "admin/complaints",
        Component: () => (
          <ProtectedRoute roles={["ADMIN"]}>
            <AdminComplaints />
          </ProtectedRoute>
        ),
      },
      {
        path: "admin/analytics",
        Component: () => (
          <ProtectedRoute roles={["ADMIN"]}>
            <AdminAnalytics />
          </ProtectedRoute>
        ),
      },
      { path: "*", Component: NotFound },
    ],
  },
]);

export default router;
