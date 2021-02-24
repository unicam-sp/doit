import React, { Component } from 'react'
import NewProjectButton from './NewProjectButton'
import NewProjectForm from "./NewProjectForm"
import './style.css';

// contiene la logica per creare un nuovo progetto

class NewProjectContainer extends Component {

    constructor(props) {
        super(props)
        this.state = {
            visible: false
        }
        this.toggleMenu = this.toggleMenu.bind(this)
        this.changeVisibility = this.changeVisibility.bind(this)
    }

    changeVisibility(e) {
        this.toggleMenu()
        e.stopPropagation()
    }

    toggleMenu() {
        this.setState({ visible: !this.state.visible })
    }

    render() {
        return (
            <div className="NewProjectContainer">
                {this.state.visible && <NewProjectForm jwt={this.props.jwt} changeVisibility={this.changeVisibility} />}
                <NewProjectButton changeVisibility={this.changeVisibility} />
            </div>
        )
    }
}

export default NewProjectContainer;