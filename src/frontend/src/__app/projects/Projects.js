import React, { Component } from 'react'
import jwt_decode from "jwt-decode"
import { isEsperto, isProgettista } from "../Utils"
import "../General.css"
import ProgettiPropositore from "./Propositore/ProgettiPropositore"
import ProgettiEsperto from "./Esperto/ProgettiEsperto"
import FlashMessage from "../components/FlashMessage/FlashMessage";

/*
	- se propositore può creare progetti e vede i progetti su cui lavora
	- se esperto può vedere i progetti su cui sta lavorando
*/

class Projects extends Component {

	constructor(props) {
		super(props)

		if (props.jwt !== '' && props.jwt !== undefined) {
			let decoded = jwt_decode(props.jwt);

			this.state = {
				username: decoded.username,
				isProgettista: isProgettista(decoded),
				isEsperto: isEsperto(decoded),
				file: null,
				error: '',
				projects: [],
				message: ''
			}
		} else {
			this.state = {
				projects: [],
				username: '',
				login_error: 'Effettuare il login'
			}
		}
	}

	componentDidMount() {
        if (this.props.location.state) {
            this.setState({
                message: this.props.location.state.message
            })
        }
    }

	render() {
		return (
			<div>
				<div className="contentStyles">
					<h1>I tuoi progetti</h1>
					<FlashMessage msg_from_parent={this.state.message} class={'msg'} />
					{this.state.login_error && <div className="error">{this.state.login_error}</div>}
					{this.state.error && <div className="error">{this.state.error}</div>}
				</div>
				{ // Propositore
				this.state.isProgettista && <ProgettiPropositore username={this.state.username} jwt={this.props.jwt} />}
				{ // Esperto
				this.state.isEsperto && <ProgettiEsperto username={this.state.username}/>}
			</div>
		)
	}
}

export default Projects;