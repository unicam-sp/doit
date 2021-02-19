import React, { Component } from 'react'
import "./style.css";

class FlashMessage extends Component {

    constructor() {
        super()
        this.state = {
            visible: false,
            show: false,
        }
    }

    shouldComponentUpdate(nextProps, nextState) {

        if(nextState.visible !== this.state.visible) return true

        if( this.props.msg_from_parent !== nextProps.msg_from_parent ) {
            this.setState({
                msg: nextProps.msg_from_parent,
                visible: true,
                show: true
            })
            // dovrebbe renderizzare qui invece non lo fa
            setTimeout(() => {
                this.setState({
                    visible: false,
                })
            }, 3000)
            return true
        }
        return false
    }

    render() {
        return (
            this.state.visible && <div className={this.props.class}>{this.props.msg_from_parent}</div>
        )
    }
}

export default FlashMessage