import React from "react";
import PropTypes from "prop-types";
import "./styles.css";

const SearchOption = ({ placeholder, handleInputChange, searchText }) => (
  <input
    className="search-option"
    type="text"
    placeholder={placeholder}
    onChange={handleInputChange}
    value={searchText}
  />
);

SearchOption.propTypes = {
  placeholder: PropTypes.string,
  handleInputChange: PropTypes.func.isRequired,
  searchText: PropTypes.string
};

export default SearchOption;
