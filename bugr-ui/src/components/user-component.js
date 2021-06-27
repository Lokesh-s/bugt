import React, { Component } from "react";
import NetworkService from "../services/network-service";

class UserComponent extends Component {
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
    		{"User Component"}
    		</div>
      );
  }
}

export default UserComponent;