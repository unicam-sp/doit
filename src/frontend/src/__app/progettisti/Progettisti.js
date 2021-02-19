import React, { Component } from 'react'
import { getUsernamesWithProfile } from "../api/ProfiloAPI";
import {
	Link
} from 'react-router-dom'
import { errHandling } from "../Utils";

class Progettisti extends Component {

	constructor(props) {
		super(props);

		this.state = {
			jwt: props.jwt,
			links: [],
			error: ''
		}
	}

	componentDidMount() {
		getUsernamesWithProfile(this.state.jwt)
			.then(res => {
				if (res.data) {
					this.setState({
						links: res.data
					})
				}
			})
			.catch(err => this.setState({error: errHandling(err)}))
	}

	render() {
		return (
			<div className="contentStyles">
				<h1>Progettisti</h1>

				{!this.state.jwt && <div className="error">Effetturare il login</div>}
				{this.state.error && <div className="error">{this.state.error}</div>}
				{
					this.state.links.map((value, index) => {
						return <Link to={{ pathname: '/user/public', state: { username: value} }}> {value} </Link>
					})
				}

			</div>
		)
	}
}

export default Progettisti;