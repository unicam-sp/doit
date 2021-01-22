import React, { Component } from "react";
import {
	Link
} from 'react-router-dom'

class ProgettoCandidatureCard extends Component {

	constructor(props) {
		super(props)

		this.state = {
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

	render() {

		return (
			<div>
				<button onClick={this.handleMouseDown}>
					{this.props.proj.titolo}
				</button>
				{
					this.state.visible && <ul>
						{
							this.props.proj.selezionatori.map(sel => {
								let path = '/user/public/' + sel.id
								return (

									<li>
										<Link to={path}>{sel.nome}</Link>
										<button>Accetta</button>
										<button>Rifiuta</button>
									</li>
								)
							})
						}
					</ul>
				}
			</div>
		)
	}
}

export default ProgettoCandidatureCard;