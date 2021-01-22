import React from "react";
import PropTypes from "prop-types";
import "./styles.css";

const DropdownContainer = ({ children }) => (
  <div className="drop-container">{children}</div>
);

DropdownContainer.propTypes = {
  children: PropTypes.node.isRequired
};

export default DropdownContainer;
