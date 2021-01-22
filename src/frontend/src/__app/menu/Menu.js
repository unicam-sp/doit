import React, { Component } from "react";
import {
  Link
} from 'react-router-dom'
import "./Menu.css";

class Menu extends Component {
  render() {
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
          <Link to='/'>Home</Link>
          <Link to='/storefront'>Vetrina</Link>
          <Link to='/projects'>I miei progetti</Link>
          <Link to='/candidature'>Candidature</Link>
        </div>
      </div>
    );
  }
}

export default Menu;