import React from "react";
import PropTypes from "prop-types";

const Option = ({ className, name, value, onOptionSelect }) => (
  <div
    className={className}
    data-name={name}
    data-value={value}
    onClick={onOptionSelect}
  >
    {value}
  </div>
);

Option.propTypes = {
  className: PropTypes.string.isRequired,
  name: PropTypes.string.isRequired,
  onOptionSelect: PropTypes.func.isRequired,
  value: PropTypes.string.isRequired
};

export default Option;
