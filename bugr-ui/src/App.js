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
      <nav className="navbar navbar-expand navbar-dark bg-dark">
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
                Add
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/Grid"} className="nav-link">
                Grid
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/User"} className="nav-link">
                User
              </Link>
            </li>
          </div>
        </nav>
       
        <div className="container mt-3">
          <Switch>
            <Route exact path={["/DashboardComponent"]} component={DashboardComponent} />
            <Route exact path="/add" component={BugComponent} />
            <Route path="/Grid" component={BugGridComponent} />
            <Route path="/User" component={UserComponent} />
          </Switch>
        </div>
        </Router>
      </div>
    );
  }
}

export default App;