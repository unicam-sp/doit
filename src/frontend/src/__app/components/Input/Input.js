import React, { Component } from 'react'
import PropTypes from 'prop-types'
import "./style.css";

class Input extends Component {
    constructor(props) {
        super(props)
        this.state = {
            className: props.className ? props.className : '',
        }
    }

    render() {
        const {  name, ...opts } = this.props
        return (
            <div className="container">
                {this.props.error && <div className="error">{this.props.error}</div>}
                <input {...opts}
                    onChange={this.props.onChange}
                    className={this.state.className}
                />
            </div>
        )
    }
}

Input.propTypes = {
    name: PropTypes.string,
    placeholder: PropTypes.string,
    type: PropTypes.string,
    className: PropTypes.string,
    value: PropTypes.string,
    handleError: PropTypes.func
}

export default Input