import React, { PureComponent } from "react";
import isEmpty from "lodash/isEmpty";
import PropTypes from "prop-types";
import Option from "../Option";
import NoOptions from "../NoOptions";
import "./styles.css";

class OptionsContainer extends PureComponent {

  onOptionSelect = (obj) => {
    this.props.handleOptionSelect(obj);
  };

  render = () => {
    const { name, searchText, selectOptions, selected } = this.props;

    const availableOptions = !searchText
      ? selectOptions
      : selectOptions.filter(obj =>
        obj.value.toLowerCase().includes(searchText.toLowerCase())
      );

    return (
      <div className="options-container">
        {!isEmpty(availableOptions) ? (
          availableOptions.map(obj => (
            <Option
              className={`option ${selected === obj.value ? "active-option" : ""}`}
              key={obj.key}
              name={name}
              value={obj.value}
              onOptionSelect={() => this.onOptionSelect(obj)}
            />
          ))
        ) : (
            <NoOptions />
          )}
      </div>
    );
  };
}

OptionsContainer.propTypes = {
  handleOptionSelect: PropTypes.func.isRequired,
  isVisible: PropTypes.bool.isRequired,
  name: PropTypes.string.isRequired,
  searchText: PropTypes.string,
  selected: PropTypes.string,
};

export default OptionsContainer;


/*  ORIGINAL

class OptionsContainer extends PureComponent {
  onOptionSelect = ({
    target: {
      dataset: { name, value }
    }
  }) => {
    this.props.handleOptionSelect({ name, value });
  };

  render = () => {
    const { name, searchText, selectOptions, selected } = this.props;

    const availableOptions = !searchText
      ? selectOptions
      : selectOptions.filter(value =>
          value.toLowerCase().includes(searchText.toLowerCase())
        );

    return (
      <div className="options-container">
        {!isEmpty(availableOptions) ? (
          availableOptions.map(value => (
            <Option
              className={`option ${selected === value ? "active-option" : ""}`}
              key={value}
              name={name}
              value={value}
              onOptionSelect={this.onOptionSelect}
            >
              {value}
            </Option>
          ))
        ) : (
          <NoOptions />
        )}
      </div>
    );
  };
}

*/