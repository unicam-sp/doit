import React, { Component } from "react"
import './style.css'

class MenuButton extends Component {
    render() {
        return (
            <button className="button"
                onMouseDown={this.props.changeVisibility}>
                +
            </button>
        );
    }
}

export default MenuButton;