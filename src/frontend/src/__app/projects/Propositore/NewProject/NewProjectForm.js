import React, { Component } from "react"
import { Redirect } from 'react-router-dom'
import './style.css'
import TextAreaAutoSize from "../../../components/TextAreaAutoSize/TextAreaAutoSize";
import Input from "../../../components/Input/Input";
import { createProject } from "../../../api/ProjectAPI";
import { errHandling } from "../../../Utils";

class NewProjectForm extends Component {

	constructor(props) {
		super(props)
		this.state = {
			titolo: '',
			descrizione: '',
			requisiti: '',
			error: '',
			created: false
		}
		this.handleSubmit = this.handleSubmit.bind(this)
		this.onChangeTitolo = this.onChangeTitolo.bind(this)
		this.onChangeDescrizione = this.onChangeDescrizione.bind(this)
		this.onChangeRequisiti = this.onChangeRequisiti.bind(this)
	}

	handleSubmit(event) {
		event.stopPropagation()
		createProject(this.props.jwt, this.state.titolo, this.state.descrizione, this.state.requisiti)
		.then(res => { 
			if(res.status === 201) {
				this.setState({
					created: true
				})
			}
		})
		.catch(err => this.setState({error: errHandling(err)}))
	}

	onChangeTitolo(event) {
		this.setState({
			titolo: event.target.value
		})
	}

	onChangeDescrizione(event) {
		this.setState({
			descrizione: event.target.value
		})
	}

	onChangeRequisiti(event) {
		this.setState({
			requisiti: event.target.value
		})
	}

	render() {

		if (this.state.created) return <Redirect to={{
            pathname: "/projects",
            state: { message: "Progetto creato!" }
        }} />

		return (
			<div className='NewProjectForm'>
				{this.state.error && <div className="error">{this.state.error}</div>}
				<form>
					Titolo
					<Input
						id='titolo'
						placeholder='titolo'
						type='text'
						value={this.state.titolo}
						className='text'
						onChange={this.onChangeTitolo}
					/>
					<div>
						Descrizione
						</div>
					<TextAreaAutoSize
						id='descrizione'
						placeholder='descrizione'
						type='text'
						value={this.state.descrizione}
						className='text'
						onChange={this.onChangeDescrizione}
					/>
					<div>
					Requisiti
					</div>
					<TextAreaAutoSize
						id='requisiti'
						placeholder='requisiti'
						type='text'
						value={this.state.requisiti}
						className='text'
						onChange={this.onChangeRequisiti}
					/>
				</form>
				<button className="button" onMouseDown={this.handleSubmit}>Crea progetto</button>
				<button className="button" onMouseDown={this.props.changeVisibility}>Annulla</button>
			</div>
		)
	}
}

export default NewProjectForm

/*

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

*/