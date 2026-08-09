import { createBrowserRouter } from "react-router";

import App from "../App.jsx";
import ProtectedRoute from "../routes/ProtectedRoute.jsx";
import Home from "../pages/Home.jsx";
import Complaints from "../pages/Complaints.jsx";
import Services from "../pages/Services.jsx";
import About from "../pages/About.jsx";
import Login from "../pages/Login.jsx";
import Register from "../pages/Register.jsx";
import Dashboard from "../pages/Dashboard.jsx";
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
      {
        path: "dashboard",
        Component: () => (
          <ProtectedRoute>
            <Dashboard />
          </ProtectedRoute>
        ),
      },
      { path: "*", Component: NotFound },
    ],
  },
]);

export default router;
