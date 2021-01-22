/*
    Un progetto ha
        - id
        - propositoreID
        - selezionatori
        - titolo
        - descrizione
        - requisiti
*/

import React, { Component } from 'react'
import ModifyProjectForm from './ModifyProjectForm'
import ProjectCard from "../ProjectCard";

/*
    View principale.
    riceve props.projects, tutti i progetti
*/
class ModifyProject extends Component {

    constructor() {
        super()

        this.state = {
            visible: false,
            projectSelected: null
        }

        this.handleMouseDown = this.handleMouseDown.bind(this)
        this.changeProjectSelected = this.changeProjectSelected.bind(this)
    }

    handleMouseDown() {
        this.setState({
            visible: !this.state.visible
        });
    }

    changeProjectSelected(proj) {
        this.setState({
            projectSelected: proj
        })
    }

    render() {
        return (
            <div>
                { this.state.projectSelected && <ModifyProjectForm
                    handleMouseDown={this.handleMouseDown}
                    menuVisibility={this.state.visible}
                    projectSelected={this.state.projectSelected}
                />
                }
                <ul>
                    {this.props.projects && this.props.projects.map(proj =>
                        <li>
                            <ProjectCard proj={proj}
                                changeProjectSelected={this.changeProjectSelected}
                                handleMouseDown={this.handleMouseDown}
                            />
                        </li>)}
                </ul>
            </div>
        )
    }
}

export default ModifyProject;