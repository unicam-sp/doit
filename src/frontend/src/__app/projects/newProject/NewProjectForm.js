import React, { Component } from "react";
import "../Project.css";

class NewProjectForm extends Component {

	state = {
		titolo: "",
		descrizione: "",
		requisiti: ""
	}

	render() {
		var visibility = "hide";

		if (this.props.menuVisibility) {
			visibility = "show";
		}

		// Comportamento simile al menu ma con effetto a comparsa invece che slide
		return (
			<div id="FormBackground"
				className={visibility}>
				<div className="FormContainer">
					<form className="Form">
						<div className="Label">
							<p>Titolo progetto:</p>
							<textarea
								type='text'
								value={this.state.titolo}
								onChange={this.handleChange}
							/>
						</div>
						<div className="Label">
							<p>Descrizione:</p>
							<textarea
								className="Descrizione"
								type='text'
								value={this.state.descrizione}
								onChange={this.handleChange}
							/>
						</div>
						<div className="Label">
							<p>Requisiti:</p>
							<textarea
								type='text'
								value={this.state.requisiti}
								onChange={this.handleChange}
							/>
						</div>
					</form>
					<button onMouseDown={this.props.handleMouseDown}>Annulla</button>
				</div>
			</div>
		);
	}
}

export default NewProjectForm;