import React, { PureComponent } from "react";
import { withStyles } from "@material-ui/styles";
import { toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";
import { Redirect } from "react-router-dom";
import { deletePlace, getPlaces } from "../rest/VisitedPlacesService";
import Card from "@material-ui/core/Card";
import CardContent from "@material-ui/core/CardContent";
import Typography from "@material-ui/core/Typography";
import CardActions from "@material-ui/core/CardActions";
import Button from "@material-ui/core/Button";
toast.configure();

toast.configure();

const styles = () => ({
  textField: {
    marginTop: "1rem",
  },
});

class CreateFinancialRequestScreen extends PureComponent {
  constructor(props) {
    super(props);
    this.state = {
      places: null,
    };
  }

  componentDidMount() {
    getPlaces().then((response) => {
      this.setState({ places: response.data });
    });
  }

  handleDelete = (id) => {
    deletePlace(id)
      .then(() => {
        this.setState({
          places: this.state.places.filter(function (place) {
            return place.id !== id;
          }),
        });
        toast.success("Place removed successfully!");
      })
      .catch(() => {
        toast.error("Some problem in removing the place");
      });
  };

  render() {
    if (localStorage.getItem("token") === null) {
      return <Redirect to="/" />;
    }

    const { classes } = this.props;
    const { places } = this.state;
    return (
      <div>
        {places ? (
          places.map((place) => {
            return (
              <Card
                style={{
                  backgroundColor: "#d3cdf7",
                  margin: "2rem 10%",
                }}
              >
                <CardContent>
                  <Typography
                    color="textSecondary"
                    gutterBottom
                    style={{ textAlign: "left" }}
                  >
                    <div style={{ display: "grid" }}>
                      <h2>
                        <strong>{place.countryCode}</strong>
                      </h2>
                      <strong>
                        City:
                        {place.city}
                      </strong>
                      <strong>
                        From Date:
                        {place.fromDate}
                      </strong>
                      <strong>
                        To Date:
                        {place.toDate}
                      </strong>
                    </div>
                  </Typography>
                </CardContent>
                <CardActions style={{ float: "right" }}>
                  <Button
                    size={"medium"}
                    variant="contained"
                    color="secondary"
                    onClick={() => this.handleDelete(place.id)}
                  >
                    Delete
                  </Button>
                </CardActions>
              </Card>
            );
          })
        ) : (
          <p>No Data</p>
        )}
      </div>
    );
  }
}

CreateFinancialRequestScreen.propTypes = {};

export default withStyles(styles)(CreateFinancialRequestScreen);
