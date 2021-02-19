import React, { Component } from 'react'
import MenuButton from './MenuButton';
import Menu from "./Menu";

// contiene la logica del menu

const menuButtonStyle = {
    fontFamily: 'sans-serif',
    position: "fixed",
    right: "20px"
}

class MenuContainer extends Component {

    constructor(props) {
        super(props);

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
                <Menu 
                    handleMouseDown={this.handleMouseDown} 
                    menuVisibility={this.state.visible}
                    jwt={this.props.jwt}
                />
                <div style={menuButtonStyle}>
                    <MenuButton handleMouseDown={this.handleMouseDown} />
                </div>
            </div>
        )
    }
}

export default MenuContainer;