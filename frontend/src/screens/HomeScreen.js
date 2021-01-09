import React, { PureComponent } from "react";
import Background from "../images/home_screen.jpg";

import DialogTitle from "@material-ui/core/DialogTitle";
import DialogContent from "@material-ui/core/DialogContent";
import Dialog from "@material-ui/core/Dialog";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import DialogActions from "@material-ui/core/DialogActions";
import { login } from "../rest/LoginService";
import { toast } from "react-toastify";

toast.configure();

class HomeScreen extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      loginModalOpen: !localStorage.getItem("token"),
      username: null,
      password: null,
      showBadMessageFlag: false,
    };
  }

  handleLogin = () => {
    const { username, password } = this.state;
    const user = {
      username: username,
      password: password,
    };
    login(user)
      .then((response) => {
        localStorage.setItem("token", response.data.access_token);
        this.setState({ loginModalOpen: false });
        toast.success("Logged in successfully!");
      })
      .catch((error) => {
        this.setState({ showBadMessageFlag: true });
        toast.error("Some problem with the auth server, try again later!");
      });
  };

  handleChange = (event) => {
    this.setState({ [event.target.id]: event.target.value });
  };
  render() {
    const {
      loginModalOpen,
      showBadMessageFlag,
      username,
      password,
    } = this.state;

    return (
      <div
        style={{
          backgroundImage: `url(${Background})`,
          height: "calc(100vh - 20px",
          backgroundPosition: "center",
          backgroundSize: "cover",
          backgroundRepeat: "no-repeat",
        }}
      >
        <h1 style={{ color: "white" }}>
          Welcome, Click on the menu to proceed!
        </h1>
        <Dialog open={loginModalOpen}>
          <DialogTitle id="form-dialog-title">
            <strong>LOGIN</strong>
          </DialogTitle>
          <DialogContent>
            <TextField
              autoFocus
              margin="dense"
              id="username"
              label="Username"
              fullWidth
              onChange={this.handleChange}
            />
            <TextField
              margin="dense"
              id="password"
              label="Password"
              type="password"
              fullWidth
              onChange={this.handleChange}
              onKeyPress={(event) => {
                if (event.key === "Enter" && username) {
                  this.handleLogin();
                }
              }}
            />
          </DialogContent>
          {showBadMessageFlag && (
            <p style={{ color: "red", paddingLeft: "1.5rem" }}>
              Bad credentials!
            </p>
          )}
          <DialogActions>
            <Button
              onClick={this.handleLogin}
              color="primary"
              variant="contained"
              disabled={!(username && password)}
            >
              LOGIN
            </Button>
          </DialogActions>
        </Dialog>
      </div>
    );
  }
}

HomeScreen.propTypes = {};

export default HomeScreen;
