export default {
  domain: "http://localhost:8080",
  resources: {
    user: "/api/v1/user",
  },
  auth: {
    signUp: "/api/v1/auth/signup",
    signIn: "/api/v1/auth/signin",
    refreshToken: "/api/v1/auth/refresh",
  },
};
