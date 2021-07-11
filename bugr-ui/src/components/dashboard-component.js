import React, { Component } from "react";
import NetworkService from "../services/network-service";
import Bugspie from "./bugs-pie";

class DashboardComponent extends Component {
  state = {
	defects: [],
    user: {}
  }

  componentDidMount() {
    NetworkService.getAllBugs(this.props.user.userName,this.props.user.authorities[0].authority)
      .then(response => {
        this.setState({
        	defects: response.data
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
  render() {
    return (
    		<Bugspie user={this.props.user}/>
      );
  }
}

export default DashboardComponent;