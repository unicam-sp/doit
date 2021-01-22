import React, { Component } from "react";
import '../Project.css';

class MenuButton extends Component {
    render() {
        return (
            <button className="Button"
                onMouseDown={this.props.handleMouseDown}>
                +
            </button>
        );
    }
}

export default MenuButton;