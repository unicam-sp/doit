import React, { Component } from 'react'
import jwt_decode from "jwt-decode"
import { getProfileOf } from "../api/ProfiloAPI";
import { errHandling, dataURItoBlob } from "../Utils";

// contiene il link per scaricare il CV
// i progetti completati

class UserPublic extends Component {

	constructor(props) {
		super(props);

		if (props.jwt !== '' && props.jwt !== undefined) {
			let decoded = jwt_decode(props.jwt);

			this.state = {
				jwt: props.jwt,
				username: decoded.username,
				visiting: props.location.state['username'],
				file_name: null,
				blob_url: null,
				info: null,
				error: ''
			}
		} else this.state = { username: '', visiting: props.location.state['username'] }

	}

	componentDidMount() {
		getProfileOf(this.state.jwt, this.state.visiting)
			.then(res => {
				if (res.data) {
					const blob = dataURItoBlob(res.data.cv.content, res.data.cv.blob_type)
					const url = URL.createObjectURL(blob)
					this.setState({
						file_name: res.data.cv.title,
						blob_url: url
					})
				}
			})
			.catch(err => this.setState({ error: errHandling(err) }))
	}

	render() {
		return (
			<div className="contentStyles">
				<h1>Profilo Pubblico</h1>
				{this.state.error && <div className="error">{this.state.error}</div>}
				{!this.state.username &&
					<div className="error">
						Effetturare il login per visualizzare il profilo di {this.state.visiting}
					</div>
				}
				<div>
					{
						this.state.visiting && <p>Profilo di {this.state.visiting}</p>
					}
					{
						this.state.blob_url && 
						<a href={this.state.blob_url} download={this.state.file_name}>Download CV</a>
					}
					{this.state.info}
				</div>

			</div>
		)
	}
}

export default UserPublic

/*
	res.data.cv.content.blob()
	.then(blob => {
		console.log("sta cosa dovrebbe attivarsi")
		let url = window.URL.createObjectURL(blob);
		let a = document.createElement('a');
		a.href = url;
		a.download = 'cv';
		a.click();
	})
*/
/*
	const a = document.createElement('a')
	a.href = url;
	a.download = res.data.cv.title || 'download'
*/