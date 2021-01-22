import React, { Component } from 'react'
import NewProjectContainer from './newProject/NewProjectContainer';
import ModifyProject from './modifyProject/ModifyProject'
import { getProjectByUserID } from '../api/ProjectAPI'
import "../General.css";

class Projects extends Component {

	state = {
		projects: [],
		errorMessage: ''
	}

	/*
		for(let k in err) {
			console.log(k);
			console.log(err[k]);
		}
	*/
	componentDidMount() {
		getProjectByUserID("abbb5d2a-52c0-44bb-af14-39ba158edbec")
			.then(projects => {
				this.setState({ projects: projects })
			})
			.catch(err => {
				this.setState({ errorMessage: err['response'].data })
			})
	}

	render() {
		return (
			<div>
				{ this.state.errorMessage &&
					<h3 className="error">
						{
							this.state.errorMessage
						}
					</h3>
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