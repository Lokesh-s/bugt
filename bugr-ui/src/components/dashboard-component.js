import React, { Component } from "react";
import NetworkService from "../services/network-service";

class DashboardComponent extends Component {
  state = {
    user: {}
  }

  componentDidMount() {
    NetworkService.getUserDetails()
      .then(response => {
        this.setState({
          user: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
  render() {
    return (
    		<div>
    		{"Dashboard Component"}
    		</div>
      );
  }
}

export default DashboardComponent;