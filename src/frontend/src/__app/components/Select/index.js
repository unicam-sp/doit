import React from "react";
import PropTypes from "prop-types";
import ClickHandler from "./ClickHandler";
import DropdownContainer from "./DropdownContainer";
import Label from "./Label";
import OptionsContainer from "./OptionsContainer";
import ShowSelection from "./ShowSelection";
import "./styles.css";

/*
  <select-container> -> Select
    <label />
    <select-box>
      
      <input-container> -> ShowSelection
        <selection-box>
          SearchOption, SelectedOption, SearchIcon, ChevronIcon
        </selection-box>
      </input-container>

      clickHandlerProps.isVisible && (
        <DropdownContainer>
          OptionsContainer
        </DropdownContainer>
      )
    
      </select-box>
  </select-container>
*/

const Select = ({
  handleChange,
  name,
  label,
  selectOptions,
  value,
  ...props
}) => (
  <ClickHandler handleChange={handleChange} selectOptions={selectOptions}>
    {clickHandlerProps => (
      <div className="select-container">
        {label && <Label>{label}</Label>}
        <div className="select-box">
          <ShowSelection {...clickHandlerProps} {...props} value={value} />
          {clickHandlerProps.isVisible && (
            <DropdownContainer>
              <OptionsContainer
                {...clickHandlerProps}
                name={name}
                selectOptions={selectOptions}
                selected={value}
              />
            </DropdownContainer>
          )}
        </div>
      </div>
    )}
  </ClickHandler>
);

Select.propTypes = {
  handleChange: PropTypes.func.isRequired,
  label: PropTypes.string,
  name: PropTypes.string.isRequired,
  placeholder: PropTypes.string,
  selectOptions: PropTypes.arrayOf(PropTypes.string.isRequired),
  value: PropTypes.string
};

export default Select;
