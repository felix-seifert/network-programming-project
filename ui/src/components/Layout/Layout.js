import React, { useEffect } from "react";
import "./Layout.css";
import Drawer from "@material-ui/core/Drawer";
import AppBar from "@material-ui/core/AppBar";
import IconButton from "@material-ui/core/IconButton";
import Toolbar from "@material-ui/core/Toolbar";
import Typography from "@material-ui/core/Typography";
import MenuIcon from "@material-ui/icons/Menu";
import Button from "@material-ui/core/Button";
import List from "@material-ui/core/List";
import {Link} from "react-router-dom";
import FormatAlignJustifyIcon from "@material-ui/icons/FormatAlignJustify";
import ListItem from "@material-ui/core/ListItem";
import ListItemIcon from "@material-ui/core/ListItemIcon";
import ListItemText from "@material-ui/core/ListItemText";

const Layout = () => {
  const [drawerOpen, setDrawerOpen] = React.useState(false);

  // useEffect(() => {
  //   // setRole(localStorage.getItem("role"));
  // });

  function toggleDrawer() {
    setDrawerOpen(!drawerOpen);
  }

  const handleLogout = () => {
    localStorage.clear();
    window.location.reload();
  };

  const list =()=>{
    return (
        <List style={{ backgroundColor: "#f2f2f2" }}>
          <Link
              to={"/create-place"}
              style={{ textDecoration: "none" }}
          >
            <ListItem button onClick={() => toggleDrawer()}>
              <ListItemIcon>
                <FormatAlignJustifyIcon />
              </ListItemIcon>
              <ListItemText primary={"Create Place"} />
            </ListItem>
          </Link>
          <Link
              to={"/map"}
              style={{ textDecoration: "none" }}
          >
            <ListItem button onClick={() => toggleDrawer()}>
              <ListItemIcon>
                <FormatAlignJustifyIcon />
              </ListItemIcon>
              <ListItemText primary={"Go to map"} />
            </ListItem>
          </Link>
          <Link
              to={"/"}
              style={{ textDecoration: "none" }}
          >
            <ListItem button onClick={() => toggleDrawer()}>
              <ListItemIcon>
                <FormatAlignJustifyIcon />
              </ListItemIcon>
              <ListItemText primary={"Home"} />
            </ListItem>
          </Link>

        </List>
    );
  }
    return (
    <div>
      <AppBar position="sticky" style={{background: 'red'}}>
        <Toolbar>
          <IconButton
            edge="start"
            color="inherit"
            aria-label="menu"
            onClick={() => toggleDrawer()}
          >
            <MenuIcon />
          </IconButton>
          <Typography style={{ flexGrow: 1 }} variant="h6">
            SOME MAP APP
          </Typography>
          <Button onClick={handleLogout} color="inherit">
            LOGOUT
          </Button>
        </Toolbar>
      </AppBar>

      <Drawer anchor={"left"} open={drawerOpen} onClose={() => toggleDrawer()}>
        {list()}
      </Drawer>
    </div>
  );
};

export default Layout;
