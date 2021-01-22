import React, { Component } from 'react'

class ProjectCard extends Component {

    constructor(props) {
        super(props)

        this.state = {
            visible: false,
        }
    }

    render() {
        return (
            <button onMouseDown={() => {
                this.props.handleMouseDown();
                this.props.changeProjectSelected(this.props.proj)
            }}>
                {this.props.proj.titolo}
            </button>
        )
    }
}

export default ProjectCard;