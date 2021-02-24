import React, { Component } from 'react'

class TextAreaAutoSize extends Component {

    constructor(props) {
        super(props)
        this.state = {
            height: 'auto'
        }
        this.handleChange = this.handleChange.bind(this)
    }

    handleChange(event) {
        event.target.style.height = 'auto'
        event.target.style.height = event.target.scrollHeight + "px"
        this.props.onChange(event)
    }

    render() {

        const style = {
            borderStyle: 'none',
            backgroundColor: 'lightblue',
            fontSize: '13px',
            padding: '5px',
            height: 'auto'
        }        

        return (
            <textarea
                className="TextAreaAutoSize"
                style={style}
                type='text'
                value={this.state.value}
                onChange={this.handleChange}
            />
        )
    }
}

export default TextAreaAutoSize;