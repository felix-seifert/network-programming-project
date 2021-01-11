## Dev Setup

* Run `npm install` in the `frontend` root directory.

* Create a `.env` file in the `frontend` root directory and paste the following content to it.

```
REACT_APP_MAPBOX_ACCESS_TOKEN='pk.eyJ1Ijoic2Fuc2thcjk1IiwiYSI6ImNraml5dWo5ZDJtZDkydnNjdWtscXZxNm0ifQ.cb8o0SXu2SJY-aqquDCwqw'
REACT_APP_AUTH_SERVER_OIDC_TOKEN_URL='http://localhost:8180/auth/realms/network-programming-project/protocol/openid-connect/token'
REACT_APP_VISITED_PLACE_URL='http://localhost:8081/api/v1/places'
```

* If applicable, adapt the auth server token URL and the URL for getting the visited places in the `.env` file.

* Start the node local server using ```npm start```

