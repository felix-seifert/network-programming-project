import React from "react";
import "./App.css";
import Layout from "./components/Layout/Layout";
import { Route, BrowserRouter } from "react-router-dom";
import MapScreen from "./screens/MapScreen";
import HomeScreen from "./screens/HomeScreen";
import PlaceCreateScreen from "./screens/PlaceCreateScreen";

function App() {
  return (
      <div className="App">
        <BrowserRouter>
          <Layout />
          <Route exact path="/" component={HomeScreen} />
          <Route
              exact
              path="/map"
              component={MapScreen}
          />
            <Route
                exact
                path="/create-place"
                component={PlaceCreateScreen}
            />
        </BrowserRouter>
      </div>
  );
}

export default App;
