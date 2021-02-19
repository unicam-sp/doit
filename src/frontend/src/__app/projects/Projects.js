import React, { Component } from 'react'
import NewProjectContainer from './newProject/NewProjectContainer';
import ModifyProject from './modifyProject/ModifyProject'
import { getProjectByUserID } from '../api/ProjectAPI'
import { errHandling } from "../Utils";
import "../General.css";

class Projects extends Component {

	state = {
		projects: [],
		error: ''
	}

	componentDidMount() {
		getProjectByUserID("abbb5d2a-52c0-44bb-af14-39ba158edbec")
			.then(projects => {
				this.setState({ projects: projects })
			})
			.catch(err => {
				this.setState({ error: errHandling(err) })
			})
	}

	render() {
		return (
			<div>
				{ this.state.error &&
					<div className="error">{this.state.error}</div>
				}
				<div className="contentStyles">
					<h1>Progetti</h1>
				</div>
				<NewProjectContainer />
				<ModifyProject projects={this.state.projects} />
			</div>
		)
	}
}

export default Projects;