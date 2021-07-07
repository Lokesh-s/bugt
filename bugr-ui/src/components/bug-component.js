import React, { Component } from "react";
import NetworkService from "../services/network-service";

class BugComponent extends Component{

constructor(props) {
   super(props);
   this.onChangeTitle = this.onChangeTitle.bind(this);
   this.onChangeDescription = this.onChangeDescription.bind(this);
   this.onChangeCreatedBy = this.onChangeCreatedBy.bind(this);
   this.onChangeCreatedOn = this.onChangeCreatedOn.bind(this);
   this.onChangeTestingType = this.onChangeTestingType.bind(this);
   this.onChangeAttachement = this.onChangeAttachement.bind(this);
   this.onChangeAssignedTo = this.onChangeAssignedTo.bind(this);
   this.onChangeStatus = this.onChangeStatus.bind(this);
   this.onChangePriority = this.onChangePriority.bind(this);
   this.saveBug = this.saveBug.bind(this);
   this.newBug = this.newBug.bind(this);

   this.state = {
     id: null,
     bugTitle: "",
     bugDescription: "",
     createdBy: "",
     createdOn:"",
     attachement: "",
     assignedTo: "",
     status:"",
     allUsers:[],
     testingType:"",
     testingTypeList:["System Testing","Unit Testing","Integration Testing"],
     priority:"",
     priorityList:["Low","Medium","High"],
     defectStatusList:[]
   };
  }

componentDidMount() {
    NetworkService.getAllUsers(this.props.user.userName)
      .then(response => {
        this.setState({
          allUsers: response.data,
          testingType: ["System Testing","Unit Testing","Integration Testing"],
          priority: ["Low","Medium","High"]
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
    
    NetworkService.getDefectStatus(this.props.user.authorities[0].authority)
    .then(response => {
      this.setState({
    	  defectStatusList: response.data
      });
      console.log(response.data);
    })
    .catch(e => {
      console.log(e);
    });
  }
 
  onChangeTitle(e) {
    this.setState({
      bugTitle: e.target.value
    });
  }

  onChangeDescription(e) {
    this.setState({
      bugDescription: e.target.value
    });
  }
 
  onChangeCreatedBy(e) {
    this.setState({
      createdBy: e.target.value
    });
  }

  onChangeCreatedOn(e) {
    this.setState({
      createdOn: e.target.value
    });
  }
 
  onChangeTestingType(e) {
    this.setState({
      testingType: e.target.value
    });
  }

  onChangeAttachement(e) {
    this.setState({
      attachement: e.target.value
    });
  }
 
   onChangeAssignedTo(e) {
    this.setState({
      assignedTo: e.target.value
    });
  }

  onChangeStatus(e) {
    this.setState({
      status: e.target.value
    });
  }
 
  onChangePriority(e) {
    this.setState({
      priority: e.target.value
    });
  }
 
  newBug() {
    this.setState({
    	id: null,
    	bugTitle: "",
    	bugDescription: "",
    	createdBy: "",
    	createdOn:"",
    	testingType: "",
    	attachement: "",
    	assignedTo: "",
    	status:"",
    	priority: ""
    });
  }
 
  saveBug() {
    var data = {
      bugTitle: this.state.bugTitle,
      bugDescription: this.state.bugDescription,
      createdBy: this.createdBy.value,
      createdOn: new Date(this.createdOn.value).getTime(),
      testingType: this.testingType.value,
      attachement: this.state.attachement,
      assignedTo: this.assignedTo.value,
      status: this.status.value,
      priority: this.priority.value
    };

    NetworkService.create(data)
      .then(response => {
        this.setState({
          id: response.data.id,
          bugTitle: response.data.title,
          bugDescription: response.data.description,
          createdBy: response.data.createdBy,
          createdOn: response.data.createdOn,
          testingType: response.data.testingType,
          attachement: response.data.attachement,
          assignedTo: response.data.assignedTo,
          status: response.data.status,
          priority: response.data.priority,
          submitted: true
        });
        console.log(response.data);
      })
      .catch(e => {
        console.log(e);
      });
  }
 
  render() {
	  const {constUsers} = this.state;
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>Bug Created successfully!</h4>
            <button className="btn btn-success" onClick={this.newBug}>
              Create
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="title">Title</label>
              <input
                type="text"
                className="form-control"
                id="title"
                required
                value={this.state.bugTitle}
                onChange={this.onChangeTitle}
                name="title"
              />
            </div>

            <div className="form-group">
              <label htmlFor="description">Description</label>
              <input
                type="text"
                className="form-control"
                id="description"
                required
                value={this.state.bugDescription}
                onChange={this.onChangeDescription}
                name="description"
              />
            </div>
            <div className="form-group">
              <label htmlFor="createdBy">Created By</label>
              <input
                type="text"
                className="form-control"
                id="createdBy"
                required
                disabled
                value={this.props.user.userName}
                name="description"
                ref = {(input)=> this.createdBy = input}/>
            </div>

            <div className="form-group">
              <label htmlFor="createdOn">Created On</label>
              <input
                type="date"
                className="form-control"
                id="createdOn"
                required
                value={this.state.createdOn}
                onChange={this.onChangeCreatedOn}
                name="createdOn"
                ref = {(input)=> this.createdOn = input}
              />
            </div>
           
            <div className="form-group">
              <label htmlFor="testingType">Testing Type</label>
          	  <select id="testingType" name="testingType" onChange={this.onChangeTestingType}
          	  		ref = {(input)=> this.testingType = input}>
		           {this.state.testingTypeList.map(optn => (
		               <option value={optn}>{optn}</option>
		            ))}
		      </select>
            </div>

            <div className="form-group">
              <label htmlFor="attachement">Attachement</label>
              <input
                type="file"
                className="form-control"
                id="attachement"
                required
                value={this.state.attachement}
                onChange={this.onChangeAttachement}
                name="attachement"
              />
            </div>
           
            <div className="form-group">
              <label htmlFor="assignedTo">Assigned To</label>
              <select id="assignedTo" name="assignedTo" onChange={this.onChangeAssignedTo}
              ref = {(input)=> this.assignedTo = input}>
		           {this.state.allUsers.map(optn => (
		               <option value={optn}>{optn}</option>
		            ))}
	           </select>
            </div>
           
            <div className="form-group">
              <label htmlFor="status">Status</label>
              <select id="status" name="status" onChange={this.onChangeStatus}
            	ref = {(input)=> this.status = input}>
		           {this.state.defectStatusList.map(optn => (
		               <option value={optn}>{optn}</option>
		            ))}
	           </select>
            </div>

            <div className="form-group">
              <label htmlFor="priority">Priority</label>
	              <select id="priority" name="priority" onChange={this.onChangePriority}
	              	ref = {(input)=> this.priority = input}>
		           {this.state.priorityList.map(optn => (
		               <option value={optn}>{optn}</option>
		            ))}
		           </select>
            </div>

            <button onClick={this.saveBug} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
    );
  }
}

export default BugComponent;