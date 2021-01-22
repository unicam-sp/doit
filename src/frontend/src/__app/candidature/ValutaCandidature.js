import React, { Component } from "react";
import { getRecruitersProject } from '../api/ProjectAPI'
import ProgettoCandidatureCard from "./ProgettoCandidatureCard";

/*	TODO

	- ottieni lista progetti e mostrala a video
		- lavoro da fare sul backend
	- ottieni UUID candidature e mostrale a video
	- iterazione 2
	- crea pagina per ogni UUID
*/
class ValutaCandidature extends Component {

	constructor() {
		super()

		this.state = {
			projects: [],
			errorMessage: '',
			visible: false,
			projectSelected: null
		}

		this.handleMouseDown = this.handleMouseDown.bind(this)

	}

	handleMouseDown() {
		this.setState({
			visible: !this.state.visible
		});
	}

	componentDidMount() {
		getRecruitersProject("bfa3a6e9-c914-4bcb-9d4b-6d0c3b51101b")
			.then(projects => {
				console.log(projects);
				this.setState({ projects: projects })
			})
			.catch(err => this.setState({ errorMessage: err.message }))
	}

	render() {

		return (
			<div className="contentStyles">
				{ this.state.errorMessage && <h3 className="error"> {this.state.errorMessage} </h3>}
				<h1>Valuta</h1>
				{
					/* LISTA PROGETTI IN CUI L'USER E' RECLUTATORE */
					this.state.projects &&
					<ul>
						{
							this.state.projects.map(proj =>
								<li>
									<ProgettoCandidatureCard proj={proj} />
								</li>
							)
						}
					</ul>
				}
			</div>
		);
	}
}

export default ValutaCandidature;