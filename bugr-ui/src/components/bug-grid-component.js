import React, { Component } from "react";
import { Button,Modal } from 'react-bootstrap';
import BugUpdateComponent from "./bug-component-update";
import NetworkService from "../services/network-service";
import 'react-bootstrap-table-next/dist/react-bootstrap-table2.min.css';
import BootstrapTable from 'react-bootstrap-table-next';
import ToolkitProvider, { CSVExport,Search } from 'react-bootstrap-table2-toolkit';
const { ExportCSVButton } = CSVExport;
const { SearchBar } = Search;

class BugGridComponent extends Component{
  state = {
     defects: [],
     showHide : false,
     idToUpdate:null,
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
		 text: 'Created Date'
		},{
		 dataField: 'targetDate',
		 text: 'Target Date'
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
 
  handleModalShowHide() {
      this.setState({ showHide: !this.state.showHide })
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
	  const rowEvents = {
		onDoubleClick: (e, row, rowIndex) => {
		    console.log(`clicked on row with index: ${row.id}`);
		    this.setState({ showHide: !this.state.showHide,
		    	idToUpdate:row.id
		    })
		  },
	  };
    return (
      <div>
      <ToolkitProvider
		  keyField="id"
		  data={ this.state.defects }
		  columns={ this.state.columns }
		  exportCSV
		  search
	  >
	  {
	    props => (
	      <div>
	        <ExportCSVButton { ...props.csvProps }>Export CSV!!</ExportCSVButton>
	        <hr />
	        <SearchBar { ...props.searchProps } />
	        <hr/>
	        <BootstrapTable { ...props.baseProps } keyField='id' rowEvents={ rowEvents }  headerClasses="header-class"/>
	      </div>
	   )
	 }
	  </ToolkitProvider>
	  
	  <Modal show={this.state.showHide}>
	      <Modal.Header closeButton onClick={() => this.handleModalShowHide()}>
	      <Modal.Title>Update Bug</Modal.Title>
	      </Modal.Header>
	      <Modal.Body><BugUpdateComponent user={this.props.user} id={this.state.idToUpdate}/></Modal.Body>
	      <Modal.Footer>
	      <Button variant="secondary" onClick={() => this.handleModalShowHide()}>
	          Close
	      </Button>
	      /*<Button variant="primary" onClick={() => this.handleModalShowHide()}>
	          Save Changes
	      </Button>*/
	      </Modal.Footer>
	  </Modal>
     </div>
    );
  }
}

export default BugGridComponent;