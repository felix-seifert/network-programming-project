import axios from "axios";

export const createPlace = (requestBody) => {
  const url = `http://${process.env.REACT_APP_VISITED_PLACE_BASE_URL}/api/v1/places`;
  let config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };
  return axios.post(url, requestBody, config);
};

export const getPlaces = () => {
  const url = `http://${process.env.REACT_APP_VISITED_PLACE_BASE_URL}/api/v1/places`;
  let config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };

  return axios.get(url, config);
};

export const deletePlace = (id) => {
  const url = `http://${process.env.REACT_APP_VISITED_PLACE_BASE_URL}/api/v1/places/${id}`;
  let config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };

  return axios.delete(url, config);
};




