
import axios from "axios";

export const login = (user) => {
  const url = `${process.env.REACT_APP_AUTH_SERVER_OIDC_TOKEN_URL}`;
  const querystring = require("querystring");

  let requestBody = querystring.stringify({
    grant_type: "password",
    username: user.username,
    password: user.password,
  });

  return axios.post(url, requestBody, {
    auth: {
      username: "frontend"
    },
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
  });
};
