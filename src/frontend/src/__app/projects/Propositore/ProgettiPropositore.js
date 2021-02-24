import React, { Component } from 'react'
import NewProjectContainer from "./NewProject/NewProjectContainer";
import ModifyProject from "./modifyProject/ModifyProject";

class ProgettiPropositore extends Component {

	constructor(props) {
		super(props)
        this.state = {}
	}

	render() {
		return (
			<div>
				<NewProjectContainer jwt={this.props.jwt} />
				<ModifyProject jwt={this.props.jwt} username={this.props.username}/>
			</div>
		)
	}
}

export default ProgettiPropositore;