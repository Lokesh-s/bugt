import React, { Component } from "react";
import NetworkService from "../services/network-service";
import Chart from "react-google-charts";

class BugsBar extends Component{
  state = {
     data: []
  	};
  
  componentDidMount() {
	    NetworkService.getBugsStatusChart()
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
    		  data={[
    		    ['Year', 'Sales', 'Expenses', 'Profit'],
    		    ['2014', 1000, 400, 200],
    		    ['2015', 1170, 460, 250],
    		    ['2016', 660, 1120, 300],
    		    ['2017', 1030, 540, 350],
    		  ]}
    		  options={{
    		    // Material design options
    		    chart: {
    		      title: 'Company Performance',
    		      subtitle: 'Sales, Expenses, and Profit: 2014-2017',
    		    },
    		  }}
    		  // For tests
    		  rootProps={{ 'data-testid': '2' }}
    		/>
    );
  }
}

export default BugsBar;