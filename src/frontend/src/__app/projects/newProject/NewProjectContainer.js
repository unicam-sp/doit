import React, { Component } from 'react'
import NewProjectButton from './NewProjectButton';
import NewProjectForm from "./NewProjectForm";
import '../Project.css'

// contiene la logica per creare un nuovo progetto

class NewProjectContainer extends Component {

    constructor(props) {
        super(props);

        // StateHook
        this.state = {
            visible: false
        };

        this.toggleMenu = this.toggleMenu.bind(this);
        this.handleMouseDown = this.handleMouseDown.bind(this);
    }

    handleMouseDown(e) {
        this.toggleMenu();
        e.stopPropagation();
    }

    toggleMenu() {
        this.setState({
            visible: !this.state.visible
        });
    }

    render() {
        return (
            <div>
                <NewProjectForm handleMouseDown={this.handleMouseDown} menuVisibility={this.state.visible} />
                <div className="NewProjectButtonContainer">
                    <NewProjectButton handleMouseDown={this.handleMouseDown} />
                </div>
            </div>
        )
    }
}

export default NewProjectContainer;