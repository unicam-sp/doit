import React, { Component } from 'react'
import {
	BrowserRouter as Router,
	Switch,
	Route,
} from 'react-router-dom'
import HomePage from "./homepage/Home";
import MenuContainer from './menu/MenuContainer';
import Projects from './projects/Projects';
import Storefront from './storefront/Storefront';
import Candidature from "./candidature/Candidature";
import Signup from './loginSignup/Signup';
import Login from "./loginSignup/Login";
import './General.css';
import UserPrivate from "./user/UserPrivate";
import UserPublic from "./user/UserPublic";
import Progettisti from "./progettisti/Progettisti";

class App extends Component {

	constructor() {
		super();

		this.state = {
			jwt: '',
		}

		this.setJwt = this.setJwt.bind(this)
	}

	setJwt(newJwt) {
		this.setState({
			jwt: newJwt
		})
	}

	render() {
		if (this.state.jwt === '') console.log("jwt is empty")
		return (
			<div>
				<Router>
					<MenuContainer jwt={this.state.jwt} />
					<Switch>
						<Route path='/' exact component={HomePage} />
						<Route path='/login'
							render={(props) => (
								<Login {...props} setJwt={this.setJwt} />
							)}
						/>
						<Route path='/signup' component={Signup} />
						<Route path='/storefront' component={Storefront} />
						<Route path='/projects'
							render={(props) => (
								<Projects {...props} key={Date.now()} jwt={this.state.jwt} />
							)}
						/>
						<Route path='/candidature' component={Candidature} />
						<Route path='/user/private'
							render={(props) => (
								<UserPrivate {...props} jwt={this.state.jwt} />
							)}
						/>
						<Route path='/user/public'
							render={(props) => (
								<UserPublic {...props} jwt={this.state.jwt} />
							)}
						/>
						<Route path='/progettisti'
							render={(props) => (
								<Progettisti {...props} jwt={this.state.jwt} />
							)}
						/>
					</Switch>
				</Router>
			</div>
		)
	}
}

export default App;
