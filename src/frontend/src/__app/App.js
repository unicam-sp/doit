import React, { Component } from 'react'
import {
  BrowserRouter as Router,
  Switch,
  Route,
} from 'react-router-dom'
import MenuContainer from './menu/MenuContainer';
import Projects from './projects/Projects';
import Storefront from './storefront/Storefront';
import Candidature from "./candidature/Candidature";
import User from "./user/User";
import './General.css';

class App extends Component {

  render() {
    return (
      <div>
        <Router>
          <MenuContainer />
          <Switch>
            <Route path='/' exact component={HomePage} />
            <Route path='/storefront' component={Storefront} />
            <Route path='/projects' component={Projects} />
            <Route path='/candidature' component={Candidature} />
            <Route path='/user/public/:id' component={User} />
          </Switch>
        </Router>
      </div>
    )
  }
}

class HomePage extends Component {
  render() {
    return (
      <div className="contentStyles">
        <h1>DOIT</h1>
      </div>
    )
  }
}

export default App;
