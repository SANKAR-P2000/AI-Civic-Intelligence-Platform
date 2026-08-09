import {
  createContext,
  useCallback,
  useEffect,
  useMemo,
  useState,
} from "react";
import authService from "../services/auth.js";
import { tokenStore } from "../services/api.js";

const AuthContext = createContext(null);

function AuthProvider({ children }) {
  const [user, setUser] = useState(null);
  const [loading, setLoading] = useState(true);

  // Restore session on mount if a token exists
  useEffect(() => {
    let active = true;
    async function restoreSession() {
      const token = tokenStore.getAccessToken();
      if (!token) {
        setLoading(false);
        return;
      }
      try {
        const me = await authService.getCurrentUser();
        if (active) setUser(me);
      } catch {
        // Token invalid/expired — clear session
        tokenStore.clear();
      } finally {
        if (active) setLoading(false);
      }
    }
    restoreSession();
    return () => {
      active = false;
    };
  }, []);

  const login = useCallback(async (credentials) => {
    const data = await authService.login(credentials);
    setUser({
      id: data.id,
      fullName: data.fullName,
      email: data.email,
      phoneNumber: data.phoneNumber,
      role: data.role,
    });
    return data;
  }, []);

  const register = useCallback(async (payload) => {
    const data = await authService.register(payload);
    return data;
  }, []);

  const logout = useCallback(async () => {
    const refreshToken = tokenStore.getRefreshToken();
    await authService.logout(refreshToken);
    setUser(null);
  }, []);

  const refreshUser = useCallback(async () => {
    const me = await authService.getCurrentUser();
    setUser(me);
    return me;
  }, []);

  const value = useMemo(
    () => ({
      user,
      loading,
      login,
      register,
      logout,
      refreshUser,
      isAuthenticated: Boolean(user),
      isAdmin: user?.role === "ADMIN",
      isCitizen: user?.role === "CITIZEN",
    }),
    [user, loading, login, register, logout, refreshUser],
  );

  return <AuthContext.Provider value={value}>{children}</AuthContext.Provider>;
}

export { AuthContext, AuthProvider };
export default AuthContext;
