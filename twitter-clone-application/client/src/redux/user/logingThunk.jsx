import {
  LOGIN_SUCCESS,
  LOGIN_FAILURE,
  GET_USER_REQUEST,
  GET_USER_SUCCSES,
  GET_USER_ERROR,
  CLOSE_LOGIN_MODAL
} from '../actions';
import {setAuthToken} from "../tokens/tokens";
import {api} from "../service/api";

export function logingThunk({ email, password }) {
  return async function(dispatch) {
    try {
      const response = await api.post('auth/login', { email: email, password });
      const token = response.token;
      if (token) {
        setAuthToken(token);
        dispatch({ type: LOGIN_SUCCESS, token });
        dispatch({ type: CLOSE_LOGIN_MODAL });
        dispatch(getUser());
      } else {
        dispatch({ type: LOGIN_FAILURE,payload:{loginError: 'Sorry. Try Again'} });
      }
    } catch (error) {
      dispatch({ type: LOGIN_FAILURE,payload:{loginError: error.response.data.message} });
    }
  };
}

export function getUser() {
  return async (dispatch) => {
    try {
      dispatch({ type: GET_USER_REQUEST });
      const user = await api.get('user/profile');

      if (user) {
        dispatch({ type: GET_USER_SUCCSES, user });
      } else {
        dispatch({ type: GET_USER_ERROR });
      }
    } catch (error) {
      dispatch({ type: GET_USER_ERROR });
    }
  };
}
