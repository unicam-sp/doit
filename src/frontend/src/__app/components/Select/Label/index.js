import React from "react";
import PropTypes from "prop-types";
import "./styles.css";

const Label = ({ children }) => <label className="label">{children}:</label>;

Label.propTypes = {
  children: PropTypes.string
};

export default Label;
