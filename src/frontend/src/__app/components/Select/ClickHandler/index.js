import React, { Component } from "react";
import PropTypes from "prop-types";

class ClickHandler extends Component {
  state = {
    isVisible: false,
    searchText: ""
  };

  componentDidMount() {
    document.addEventListener("mousedown", this.handleClickOutside);
  }

  componentWillUnmount() {
    document.removeEventListener("mousedown", this.handleClickOutside);
  }

  handleClickOutside = ({ target }) => {
    if (
      this.state.isVisible &&
      this.wrapperRef &&
      !this.wrapperRef.contains(target)
    ) {
      this.handleSelectClose();
    }
  };

  handleInputChange = ({ target: { value } }) => {
    this.setState({ searchText: value });
  };

  handleSelectClose = () => {
    this.setState({ isVisible: false });
  };

  handleSelectClick = () => {
    this.setState(prevState => ({ isVisible: !prevState.isVisible }));
  };

  handleOptionSelect = props => {
    this.setState({ isVisible: false, searchText: "" }, () =>
      this.props.handleChange({ ...props })
    );
  };

  render = () => (
    <div style={{width: "70%", float: "left"}} ref={node => (this.wrapperRef = node)}>
      {this.props.children({
        isVisible: this.state.isVisible,
        handleInputChange: this.handleInputChange,
        handleSelectClick: this.handleSelectClick,
        handleOptionSelect: this.handleOptionSelect,
        searchText: this.state.searchText
      })}
    </div>
  );
}

ClickHandler.propTypes = {
  handleChange: PropTypes.func.isRequired,
  children: PropTypes.func.isRequired
};

export default ClickHandler;
