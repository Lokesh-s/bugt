import React, { Component } from "react";
import NetworkService from "../services/network-service";
import Chart from "react-google-charts";

class BugsBar extends Component{
  state = {
     data: []
  	};
  
  componentDidMount() {
	    NetworkService.getBugsForUser()
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
    		  chartType="Bar"
    		  loader={<div>Loading Chart</div>}
    		  data={this.state.data}
    		  options={{
    		    // Material design options
    		    chart: {
    		      title: 'Defect status based on users',
    		      subtitle: 'X-axis: Name, Y-axis: Defect count',
    		    },
    		  }}
    		  // For tests
    		  rootProps={{ 'data-testid': '2' }}
    		/>
    );
  }
}

export default BugsBar;