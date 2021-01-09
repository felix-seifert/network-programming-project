
import axios from "axios";

export const login = (user) => {
  const url = `http://${process.env.REACT_APP_AUTH_SERVER}:8180/auth/realms/network-programming-project/protocol/openid-connect/token`;
  const querystring = require("querystring");

  let requestBody = querystring.stringify({
    grant_type: "password",
    username: user.username,
    password: user.password,
  });

  return axios.post(url, requestBody, {
    auth: {
      username: "visited-v3",
      // password: '9e61b371-54b3-4a65-89c6-a8d9d97bcca9'
    },
    headers: {
      "content-type": "application/x-www-form-urlencoded",
    },
  });
};
