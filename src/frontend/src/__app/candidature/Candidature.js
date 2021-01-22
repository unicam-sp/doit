import React, { Component } from "react"
import ValutaCandidature from "./ValutaCandidature";
import InviaCandidatura from "./InviaCandidatura";

class Candidature extends Component {

	constructor(props) {
		super(props);
		
		this.state = {
			valuta: false,
			invia: false,
		}

		this.openValuta = this.openValuta.bind(this);
		this.openInvia = this.openInvia.bind(this);
    }

	openValuta() {
		this.setState({
			valuta: true,
			invia: false
		})
	}

	openInvia() {
		this.setState({
			valuta: false,
			invia: true
		})
	}

	render() {

		return (
			<div className="contentStyles">
				<h1>Candidature</h1>
				<div>
					<button onClick={this.openInvia}>Invia</button>
					<button onClick={this.openValuta}>Valuta</button>
				</div>
				{ this.state.valuta && <ValutaCandidature /> }
				{ this.state.invia && <InviaCandidatura /> }
			</div>
		);
	}
}

export default Candidature;