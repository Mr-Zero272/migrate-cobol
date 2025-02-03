import { type PayloadAction, createSlice } from '@reduxjs/toolkit';

export type Token = {
  token: string;
  expiration: number;
};

const initialState: Token = {
  token: '',
  expiration: 0,
};

const tokenSlices = createSlice({
  name: 'token',
  initialState: initialState,
  reducers: {
    setToken(state, action: PayloadAction<string>) {
      state.token = 'Bearer ' + action.payload;
      state.expiration = Date.now() / 1000 + 1 * 24 * 60 * 60;
      console.log('token', state.token, state.expiration);
      localStorage.setItem('token', JSON.stringify(state));
    },
    resetState(state) {
      state.token = '';
      state.expiration = 0;
    },
  },
});

export const { setToken, resetState } = tokenSlices.actions;

export default tokenSlices.reducer;
