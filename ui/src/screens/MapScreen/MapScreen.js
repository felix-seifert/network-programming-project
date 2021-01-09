import "./MapScreen.css";
import React, { useRef, useEffect } from "react";
import mapboxgl from "mapbox-gl";
import Backdrop from "@material-ui/core/Backdrop";
import CircularProgress from "@material-ui/core/CircularProgress";
import { getPlaces } from "../../rest/VisitedPlacesService";
import { toast } from "react-toastify";
import Redirect from "react-router-dom/es/Redirect";
toast.configure();

mapboxgl.accessToken = process.env.REACT_APP_MAPBOX_ACCESS_TOKEN;

function getDays(date1, date2) {
  var difference = date1.getTime() - date2.getTime();
  return Math.abs(Math.ceil(difference / (1000 * 3600 * 24)));
}

const MapScreen = () => {
  const mapContainerRef = useRef(null);
  const [places, setPlaces] = React.useState([]);
  const [isLoading, setisLoading] = React.useState(false);

  const fetchAndMarkPlaces = () => {
    setisLoading(true);
    getPlaces()
      .then((response) => {
        console.log(response);
        let markerPlaces = response.data;
        setPlaces(response.data);
        toast.success("Places fetched successfully!!");

        const map = new mapboxgl.Map({
          container: mapContainerRef.current,
          // See style options here: https://docs.mapbox.com/api/maps/#styles
          style: "mapbox://styles/mapbox/streets-v11",
          center: [-104.9876, 39.7405],
          zoom: 2.5,
        });

        for (let i = 0; i < markerPlaces.length; i++) {
          var el = document.createElement("div");
          el.className = "marker";
          el.style.height = `${
            getDays(
              new Date(markerPlaces[i].fromDate),
              new Date(markerPlaces[i].toDate)
            ) * 10
          }px`;
          el.style.width = `${
            getDays(
              new Date(markerPlaces[i].fromDate),
              new Date(markerPlaces[i].toDate)
            ) * 10
          }px`;
          console.log("+++++++++++++++", el.style.width);
          var marker = new mapboxgl.Marker(el)
            .setLngLat([markerPlaces[i].longitude, markerPlaces[i].latitude])
            .addTo(map);
        }

        // add navigation control (the +/- zoom buttons)
        map.addControl(new mapboxgl.NavigationControl(), "bottom-right");
      })
      .catch((err) => {
        console.log(err);
        toast.error("Some problem with fetching the places");
      });
  };

  const handleClose = () => {
    setisLoading(false);
  };

  // initialize map when component mounts

  useEffect(() => {
    fetchAndMarkPlaces();

    // clean up on unmount
    // return () => map.remove();
  }, []); // eslint-disable-line react-hooks/exhaustive-deps

  if (localStorage.getItem("token") === null) {
    return <Redirect to="/" />;
  } else {
    return (
      <div>
        <Backdrop open={isLoading} onClick={handleClose}>
          <CircularProgress color="inherit" />
        </Backdrop>
        <div className="map-container" ref={mapContainerRef} />
      </div>
    );
  }
};

export default MapScreen;
