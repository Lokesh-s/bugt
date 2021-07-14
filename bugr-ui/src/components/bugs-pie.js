import React, { Component } from "react";
import NetworkService from "../services/network-service";
import Chart from "react-google-charts";

class BugsPie extends Component{
  state = {
     data: []
  	};
  
  componentDidMount() {
	    NetworkService.getBugsStatusChart(this.props.user.userName,this.props.user.authorities[0].authority)
	      .then(response => {
	        this.setState({
	        	data: response.data
	        });
	        console.log(response.data);
	      })
	      .catch(e => {
	        console.log(e);
	      });
	  }

  render() {
    return (
    		<Chart
    		  width={'500px'}
    		  height={'300px'}
    		  chartType="PieChart"
    		  loader={<div>Loading Chart</div>}
    		  data={this.state.data}
    		  options={{
    		    title: 'Defect percentage based on status',
    		    // Just add this option
    		    is3D: true,
    		  }}
    		  rootProps={{ 'data-testid': '2' }}
    		/>
    );
  }
}

export default BugsPie;