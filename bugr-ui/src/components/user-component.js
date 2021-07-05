import React, { Component } from "react";
import NetworkService from "../services/network-service";

class UserComponent extends Component {
	constructor(props) {
	   super(props);
	   this.onChangeName = this.onChangeName.bind(this);
	   this.onChangePassword = this.onChangePassword.bind(this);
	   this.onChangeRole = this.onChangeRole.bind(this);
	   this.saveUser = this.saveUser.bind(this);
	   //this.newBug = this.newBug.bind(this);

	   this.state = {
	     id: null,
	     userName: "",
	     password: "",
	     roles: "",
	     roleList:["Manager","Tester","Developer"],
	   };
	}
	
	onChangeName(e) {
		this.setState({
			userName: e.target.value
	    });
	}

	onChangePassword(e) {
		this.setState({
			password: e.target.value
		});
	}
	
	onChangeRole(e) {
		this.setState({
			roles: e.target.value
		});
	}
	
	newUser() {
	    this.setState({
	    	id: null,
	    	userName: "",
	    	password: "",
	    	roles: ""
	    });
	}
	
	saveUser() {
	    var data = {
	      userName: this.state.userName,
	      password: this.state.password,
	      roles: this.roles.value,
	    };

	    NetworkService.createUser(data)
	      .then(response => {
	        this.setState({
	          id: response.data.id,
	          userName: response.data.userName,
	          password: response.data.password,
	          roles: response.data.roles,
	          submitted: true
	        });
	        console.log(response.data);
	      })
	      .catch(e => {
	        console.log(e);
	      });
	  }

  render() {
    return (
      <div className="submit-form">
        {this.state.submitted ? (
          <div>
            <h4>User Created successfully!</h4>
            <button className="btn btn-success" onClick={this.newUser}>
              Add User
            </button>
          </div>
        ) : (
          <div>
            <div className="form-group">
              <label htmlFor="userName">User Name</label>
              <input
                type="text"
                className="form-control"
                id="userName"
                required
                value={this.state.userName}
                onChange={this.onChangeName}
                name="userName"
              />
            </div>

            <div className="form-group">
              <label htmlFor="password">Password</label>
              <input
                type="password"
                className="form-control"
                id="password"
                required
                value={this.state.password}
                onChange={this.onChangePassword}
                name="password"
              />
            </div>
            <div className="form-group">
              <label htmlFor="role">Role</label>
          	  <select id="role" name="role" onChange={this.onChangeRole}
          	  		ref = {(input)=> this.roles = input}>
		           {this.state.roleList.map(optn => (
		               <option value={optn}>{optn}</option>
		            ))}
		      </select>
            </div>
            <button onClick={this.saveUser} className="btn btn-success">
              Submit
            </button>
          </div>
        )}
      </div>
      );
  }
}

export default UserComponent;