import React, { Component } from "react";
import { Switch, Route, Link,BrowserRouter as Router } from "react-router-dom";
import "./App.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import NetworkService from "./services/network-service";
import UserComponent from "./components/user-component";
import DashboardComponent from "./components/dashboard-component";
import BugComponent from "./components/bug-component";
import BugGridComponent from "./components/bug-grid-component";

class App extends Component {

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
      <Router>
      <nav className="navbar navbar-expand navbar-dark bg-primary">
          <Link to={"/dashboard"} className="navbar-brand">
            Bug R
          </Link>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/dashboard"} className="nav-link">
                Dashboard
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/Add"} className="nav-link">
                Add Bug
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/Grid"} className="nav-link">
                Bugs
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/User"} className="nav-link">
                Users
              </Link>
            </li>
          </div>
          <div class="navbar-collapse collapse w-100 order-3 dual-collapse2">
	          <ul class="navbar-nav ml-auto">
		          <li className="nav-item">
		           		<a class="nav-link disabled" href="#">{"Hi "+this.state.user.userName}</a>
		           </li>
		           <li className="nav-item">
			           <a class="nav-link" href="/logout">Logout</a>
			       </li>
	          </ul>
	      </div>
        </nav>
       
        <div className="container mt-3">
          <Switch>
            <Route exact path="/dashboard" render={(props) => (
            	    <DashboardComponent {...props} user={this.state.user} />
            )} />
            <Route exact path="/add" render={(props) => (
            	    <BugComponent {...props} user={this.state.user} />
            )} />
            <Route exact path="/Grid" render={(props) => (
            	    <BugGridComponent {...props} user={this.state.user} />
            )} />
            <Route path="/User" component={UserComponent} />
          </Switch>
        </div>
        </Router>
      </div>
    );
  }
}

export default App;