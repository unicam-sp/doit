import React, { Component } from "react";
import { getRecruitersProject } from '../api/ProjectAPI'

class InviaCandidatura extends Component {

	state = {
		projects: [],
		errorMessage: ''
	}

	componentDidMount() {
		getRecruitersProject("recruiterID")
			.then(projects => { this.setState({ projects: projects }) })
			.catch(err => this.setState({ errorMessage: err.message }))
	}

	render() {

		return (
			<div className="contentStyles">
				{ this.state.errorMessage && <h3 className="error"> {this.state.errorMessage} </h3>}
				<h1>Invia</h1>
                
			</div>
		);
	}
}

export default InviaCandidatura;