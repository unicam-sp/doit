import React from "react";
import PropTypes from "prop-types";
import "./styles.css";

const SelectedOption = ({ placeholder, value }) => (
  <div className={`selection ${value ? "active-selection" : ""}`}>
      Selezionato: {!value ? "" : value}
  </div>
);

SelectedOption.propTypes = {
  placeholder: PropTypes.string.isRequired,
  value: PropTypes.string.isRequired
};

export default SelectedOption;

/* ORIGINAL

  <div className={`selection ${value ? "active-selection" : ""}`}>
    {!value ? placeholder : value}
  </div>

*/