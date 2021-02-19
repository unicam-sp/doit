import React, { Component } from "react";
import {
	Link
} from 'react-router-dom'
import "./Menu.css";
import jwt_decode from "jwt-decode";

class Menu extends Component {

	constructor(props) {
        super(props);
        this.state = {
            jwt: props.jwt === '' ? '' : props.jwt
        }
    }

	render() {

		if (this.state.jwt !== '') {
			let decoded = jwt_decode(this.state.jwt);
			console.log(decoded)
			let decodedHeader = jwt_decode(this.state.jwt, { header: true });
			console.log(decodedHeader);
		}

		var visibility = "hide";

		if (this.props.menuVisibility) {
			visibility = "show";
		}

		// cambiando il nome alla classe del div si triggherano i css che spostano il menu con animazione fatta dai css
		return (
			<div id="flyoutMenu"
				onMouseDown={this.props.handleMouseDown}
				className={visibility}>
				<div className="menuContent listNoStyle">
					<div className="loginSignin">
						<Link to='/login'>Login</Link>
						<Link to='/signup'>Registrazione</Link>
						<Link to='/user/private'>Profilo</Link>
					</div>
					<Link to='/'>Home</Link>
					<Link to='/storefront'>Vetrina</Link>
					<Link to='/projects'>I miei progetti</Link>
					<Link to='/candidature'>Candidature</Link>
					<Link to='/progettisti'>I progettisti</Link>
				</div>
			</div>
		);
	}
}

/*
	{this.props.jwt === '' && <Link to='/login'>Login</Link>}
	{this.props.jwt === '' && <Link to='/signup'>Registrazione</Link>}
	{this.props.jwt !== '' && <Link to='/user/private/:id'>Profilo</Link>}
*/

export default Menu;