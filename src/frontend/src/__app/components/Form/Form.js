import React, { Component } from 'react'
import PropTypes from 'prop-types'
import Input from "../Input/Input";
import "./style.css";

class Form extends Component {

    render() {

        const inputs = this.props.inputs.map(
            ({ id, name, placeholder, type, value, className, onClick, handleChange, handleError, error }, index) => {
                if(type === 'text' || type === 'password') return (
                    <Input
                        key={index}
                        id={id}
                        name={name}
                        placeholder={placeholder}
                        type={type}
                        value={value}
                        className={type}
                        onChange={handleChange}
                        error={error}
                    />
                )
                if(type === 'button') return (
                    <Input
                        key={index}
                        id={id}
                        name={name}
                        placeholder={placeholder}
                        type={type}
                        value={value}
                        className={type}
                        onClick={onClick}
                        error={error}
                    />
                )

                return null
            }
        )
        return (
            <form className="Form" {...this.props} >
                {inputs}
                {this.props.error && <div className="error">{this.props.error}</div>}
            </form>
        )
    }
}

Form.propTypes = {
    name: PropTypes.string,
    inputs: PropTypes.array,
    error: PropTypes.string
}

export default Form