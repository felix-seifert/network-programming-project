import axios from "axios";

export const createPlace = (requestBody) => {
  const url = `${process.env.REACT_APP_VISITED_PLACE_URL}`;
  let config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };
  return axios.post(url, requestBody, config);
};

export const getPlaces = () => {
  const url = `${process.env.REACT_APP_VISITED_PLACE_URL}`;
  let config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };

  return axios.get(url, config);
};

export const deletePlace = (id) => {
  const url = `${process.env.REACT_APP_VISITED_PLACE_URL}/${id}`;
  let config = {
    headers: {
      Authorization: `Bearer ${localStorage.getItem("token")}`,
    },
  };

  return axios.delete(url, config);
};




