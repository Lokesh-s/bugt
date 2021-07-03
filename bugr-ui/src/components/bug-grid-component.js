import React, { Component } from "react";
import NetworkService from "../services/network-service";
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import ToolkitProvider, { CSVExport } from 'react-bootstrap-table2-toolkit';
const { ExportCSVButton } = CSVExport;

class BugGridComponent extends Component{
  state = {
     defects: [],
     columns : [{
		 dataField: 'id',
		 text: 'Bug ID',
		 sort: true
		}, {
		 dataField: 'bugTitle',
		 text: 'Bug Title',
		 sort: true
		}, {
		 dataField: 'bugDescription',
		 text: 'Bug Description'
		}, {
		 dataField: 'createdBy',
		 text: 'Created By'
		}, {
		 dataField: 'createdOn',
		 text: 'Created On'
		}, {
		 dataField: 'testingType',
		 text: 'Testing Type'
		},{
		 dataField: 'assignedTo',
		 text: 'Assigned To'
		},{
		 dataField: 'status',
		 text: 'Status'
		},{
		 dataField: 'priority',
		 text: 'Priority'
		}]
  	};
 
 

  componentDidMount() {
    NetworkService.getAll()
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
      <div>
      <ToolkitProvider
		  keyField="id"
		  data={ this.state.defects }
		      columns={ this.state.columns }
		  exportCSV
	  >
	  {
	    props => (
	      <div>
	        <ExportCSVButton { ...props.csvProps }>Export CSV!!</ExportCSVButton>
	        <hr />
	        <BootstrapTable { ...props.baseProps } headerClasses="header-class"/>
	      </div>
	   )
	 }
	  </ToolkitProvider>
     </div>
    );
  }
}

export default BugGridComponent;