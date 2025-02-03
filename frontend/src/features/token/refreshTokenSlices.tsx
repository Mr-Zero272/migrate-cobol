import { type PayloadAction, createSlice } from "@reduxjs/toolkit";
import { setToken } from "./tokenSlices";

export type RefreshToken = {
  refreshToken: string;
  expiration: number;
};

const initialState: RefreshToken = {
  refreshToken: "",
  expiration: 0,
};

const refreshTokenSlices = createSlice({
  name: "refreshToken",
  initialState: initialState,
  reducers: {
    refreshToken(state) {
      fetch("http://localhost:8080/auth/v1/refresh", {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
        },
        body: JSON.stringify(state),
      })
        .then((response) => {
          if (response.ok) {
            return response.json();
          } else {
            throw new Error("Failed to refresh token");
          }
        })
        .then((data) => {
          setToken(data.token);
        });
    },
    setRefreshToken(state, action: PayloadAction<string>) {
      state.expiration = Date.now() / 1000 + 30 * 24 * 60 * 60;
      state.refreshToken = action.payload;
      localStorage.setItem("refreshToken", JSON.stringify(state));
    },
    setExpiration(state, action: PayloadAction<number>) {
      state.expiration = action.payload;

      localStorage.setItem(
        "refreshToken",
        JSON.stringify({
          refreshToken: state.refreshToken,
          expiration: state.expiration,
        })
      );
    },
  },
});

export const { refreshToken, setRefreshToken, setExpiration } =
  refreshTokenSlices.actions;

export default refreshTokenSlices.reducer;
