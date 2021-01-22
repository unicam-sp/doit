import React from "react";
import PropTypes from "prop-types";

const ChevronIcon = ({ isVisible }) => (
  <div className="icon-container">
    <svg
      height="10"
      width="10"
      className={`svg-icon svg-icon-${isVisible ? "90" : "270"}`}
      viewBox="0 0 20 20"
    >
      <path
        fill="#a9a9a9"
        stroke="#a9a9a9"
        strokeWidth="10%"
        d="M14.989,9.491L6.071,0.537C5.78,0.246,5.308,0.244,5.017,0.535c-0.294,0.29-0.294,0.763-0.003,1.054l8.394,8.428L5.014,18.41c-0.291,0.291-0.291,0.763,0,1.054c0.146,0.146,0.335,0.218,0.527,0.218c0.19,0,0.382-0.073,0.527-0.218l8.918-8.919C15.277,10.254,15.277,9.784,14.989,9.491z"
      />
    </svg>
  </div>
);

ChevronIcon.propTypes = {
  isVisible: PropTypes.bool.isRequired
};

export default ChevronIcon;
