import React, { Component } from 'react'
import jwt_decode from "jwt-decode"
import "./style.css";
import TextAreaAutoSize from "../components/TextAreaAutoSize/TextAreaAutoSize";
import { loadFile } from "../api/ProfiloAPI";
// import { getAllProperties, getEnumProperties } from "../Utils";

/*
	- possibilita' di caricare il CV
	- lista dei progetti completati
*/

class UserPrivate extends Component {

	constructor(props) {
		super(props);

		if (props.jwt !== '' && props.jwt !== undefined) {
			let decoded = jwt_decode(props.jwt);

			this.state = {
				username: decoded.username,
				file: null,
				error: ''
			}
		} else {
			this.state = {
				username: ''
			}
		}
		this.save = this.save.bind(this)
		this.handleFileInput = this.handleFileInput.bind(this)
	}

	componentDidMount() {
		if (this.state.username) {
			// TODO carica progetti completati
		}
	}

	save(event) {
		// salva file
		// salva textarea info
		if(this.state.file == null) {
			return this.setState({ error: 'Il file non può superare essere vuoto'})
		}
		loadFile(this.props.jwt, this.state.file)
	}

	handleFileInput(event) {
		const blob = event.target.files[0]
		if (blob.size > 10455040) {
			this.setState({ error: 'Il file non può superare i 10MB'})
		} else {
			this.setState({ file: blob })
		}
	}

	render() {
		return (
			<div className="contentStyles">
				<h1>Profilo Privato</h1>
				{this.state.error && <div className="error">{this.state.error}</div>}
				{
					!this.state.username ? (
						<div className="error">Effetturare il login</div>
					) : (
							<div>
								<div className="fileuploader">
									<input type="file" onChange={this.handleFileInput} accept='application/pdf'/>
									<TextAreaAutoSize />
									<button onClick={e => this.save(e)} className="button">
										salva modifiche
									</button>
								</div>
							</div>
						)
				}
			</div>
		)
	}
}

export default UserPrivate;