import React, { PureComponent } from "react";

class HomeScreen extends PureComponent {
    constructor(props) {
        super(props);
        this.state = {
            // loginModalOpen: !localStorage.getItem("role"),
            // username: null,
            // password: null,
            // showBadMessageFlag: false,
        };
    }

    // handleLogin = () => {
    //     const { username, password } = this.state;
    //     const user = {
    //         username: username,
    //         password: password,
    //     };
    //     login(user)
    //         .then((response) => {
    //             localStorage.setItem("username", response.data.username);
    //             localStorage.setItem("role", response.data.role);
    //             this.setState({ loginModalOpen: false });
    //         })
    //         .catch((error) => {
    //             this.setState({ showBadMessageFlag: true });
    //             console.log(error);
    //         });
    // };
    //
    // handleChange = (event) => {
    //     this.setState({ [event.target.id]: event.target.value });
    // };
    render() {
        const { loginModalOpen, showBadMessageFlag } = this.state;

        return (
            <div>
                <h1>Welcome, Click on the menu to proceed!</h1>
            </div>
        );
    }
}

HomeScreen.propTypes = {};

export default HomeScreen;
