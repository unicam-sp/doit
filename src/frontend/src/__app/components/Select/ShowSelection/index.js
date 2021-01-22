import React from "react";
import PropTypes from "prop-types";
import SearchOption from "../SearchOption";
import SelectedOption from "../SelectedOption";
import "./styles.css";

const ShowSelection = ({
	handleInputChange,
	handleSelectClick,
	isVisible,
	isSearchable,
	placeholder,
	searchText,
	showIcon,
	value
}) => (
	<div className={`input-container ${isVisible ? "focused" : ""}`}>
		<div className="selection-box" onClick={handleSelectClick}>
			{isSearchable &&
				<SearchOption
					placeholder={placeholder}
					handleInputChange={handleInputChange}
					value={searchText}
				/>

			}
			<SelectedOption placeholder={placeholder} value={value} />
		</div>
	</div>
);

ShowSelection.propTypes = {
	handleInputChange: PropTypes.func.isRequired,
	handleSelectClick: PropTypes.func.isRequired,
	isVisible: PropTypes.bool.isRequired,
	isSearchable: PropTypes.bool,
	placeholder: PropTypes.string,
	searchText: PropTypes.string,
	showIcon: PropTypes.bool,
	value: PropTypes.string
};

export default ShowSelection;

/*	ORIGINAL

	{isSearchable && !value ? (
		<SearchOption
			placeholder={placeholder}
			handleInputChange={handleInputChange}
			value={searchText}
		/>
	) : (
			<SelectedOption placeholder={placeholder} value={value} />
		)
	}
	{
		showIcon === false ? null :
			isSearchable && !value ? ( <SearchIcon /> ) : ( <ChevronIcon isVisible={isVisible} /> )
	}
*/