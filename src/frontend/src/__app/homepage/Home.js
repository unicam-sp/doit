import React, { Component } from 'react'
import FlashMessage from "../components/FlashMessage/FlashMessage";

class HomePage extends Component {

	constructor() {
        super();
        this.state = {
            message: '',
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
			<div className="contentStyles">
				<FlashMessage msg_from_parent={this.state.message} class={'msg'} />
				<h1>DOIT</h1>
			</div>
		)
	}
}

export default HomePage;