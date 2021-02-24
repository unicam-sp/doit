import React, { Component } from 'react'
import ModifyProjectForm from './ModifyProjectForm'
import ProjectCard from "./ProjectCard"
import { getProjectByUsername } from "../../../api/ProjectAPI"
import { errHandling } from "../../../Utils"
import "./style.css"

class ModifyProject extends Component {

    constructor(props) {
        super(props)
        this.state = {
            jwt: props.jwt,
            username: props.username,
            visible: false,
            projectSelected: null,
            error: '',
            progetti: []
        }
        this.fetchData = this.fetchData.bind(this)
        this.handleMouseDown = this.handleMouseDown.bind(this)
        this.changeProjectSelected = this.changeProjectSelected.bind(this)
    }

    shouldComponentUpdate(nextProps, nextState) {
        if (this.state.progetti !== nextState.progetti) return true
        if (this.state.visible !== nextState.visible) return true
        return false
    }

    componentDidMount() {
        getProjectByUsername(this.state.jwt, this.state.username)
        .then(res => {
            console.log(res)
            this.setState({ progetti: res })
        })
        .catch(err => this.setState({ error: errHandling(err) }))
    }

    async fetchData() {
        
    }

    handleMouseDown() {
        this.setState({
            visible: !this.state.visible
        })
    }

    changeProjectSelected(proj) {
        this.setState({
            projectSelected: proj
        })
    }

    render() {
        return (
            <div className="Container">
                {this.state.error && <div className="error">{this.state.error}</div>}
                { this.state.projectSelected && this.state.visible && <ModifyProjectForm
                    handleMouseDown={this.handleMouseDown}
                    projectSelected={this.state.projectSelected}
                    jwt={this.state.jwt}
                />
                }
                {this.state.progetti.map((proj, index) =>
                    <div className="CardsContainer">
                        <ProjectCard proj={proj}
                            changeProjectSelected={this.changeProjectSelected}
                            handleMouseDown={this.handleMouseDown}
                        />
                    </div>)
                }
            </div>
        )
    }
}

export default ModifyProject