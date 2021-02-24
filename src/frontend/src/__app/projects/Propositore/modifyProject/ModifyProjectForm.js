import React, { Component } from "react"
import Select from '../../../components/Select'
import "../../Project.css"
import { getExperts } from "../../../api/UserAPI"
import { modifyProject } from "../../../api/ProjectAPI"
import { getUsernameById } from "../../../api/SelezionatoreAPI"
import "./style.css"

class ModifyProjectForm extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: props.projectSelected.id,
            selezionatori: [],
            titolo: props.projectSelected.titolo,
            descrizione: props.projectSelected.descrizione,
            requisiti: props.projectSelected.requisiti,
            selezionatoreSelezionatoObj: {
                key: 'None',
                value: 'None'
            },
            experts: [],
            expertsSelectDropDown: []
        }
        this.modify = this.modify.bind(this)
        this.handleChange = this.handleChange.bind(this)
        this.removeSelezionatore = this.removeSelezionatore.bind(this)
        this.selectSelezionatore = this.selectSelezionatore.bind(this)
        this.addSelezionatore = this.addSelezionatore.bind(this)
    }

    componentDidMount() {
        console.log("getting experts")
        getExperts(this.props.jwt)
            .then(json => {
                let output = []
                let arr = json.data
                arr.forEach(obj => {
                    output.push({ value: obj })
                })
                this.setState({
                    experts: arr,
                    expertsSelectDropDown: output
                })
            })
            .catch(err => this.setState({ errorMessage: err.message }))
        
        console.log('nome degli esperti presi come selezionatori')
        this.props.projectSelected.selezionatori.forEach( val => {
            console.log(val.esperto_id)
            getUsernameById(this.props.jwt, val.esperto_id)
            .then(res => {
                let sel = this.state.selezionatori
                sel.push(res.data)
                this.setState({
                    selezionatori: sel
                })
            })
            .catch(err => console.log(err))
        })
    } 

    handleChange(event) {
        // event.preventDefault(); NO
        this.setState({ [event.target.name]: event.target.value })
    }

    modify() {
        modifyProject(this.props.jwt,
            this.state.id,
            this.state.titolo,
            this.state.descrizione,
            this.state.requisiti,
            this.state.selezionatori)
            .then(res => { console.log(res) })
            .catch(err => this.setState({ errorMessage: err.message }))
    }

    selectSelezionatore(event) {
        this.setState({
            selezionatoreSelezionatoObj: event
        })
    }

    addSelezionatore() {
        let selezionatore_name = this.state.selezionatoreSelezionatoObj.value
        this.setState({
            selezionatori: this.state.selezionatori.concat(selezionatore_name)
        })
    }

    removeSelezionatore(sel) {
        let filterResult = this.state.selezionatori.filter((arr) => {
            return arr.id !== sel.id
        })
        this.setState({
            selezionatori: filterResult
        })
    }

    render() {
        return (
            <div className="FormBackground">
                <div className="FormContainer">
                    <form className="Form">
                        <div className="Label">
                            <p>Titolo progetto:</p>
                            <textarea
                                name="titolo"
                                type='text'
                                value={this.state.titolo}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="Label">
                            <p>Descrizione:</p>
                            <textarea
                                name="descrizione"
                                className="Descrizione"
                                type='text'
                                value={this.state.descrizione}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="Label">
                            <p>Requisiti:</p>
                            <textarea
                                name="requisiti"
                                type='text'
                                value={this.state.requisiti}
                                onChange={this.handleChange}
                            />
                        </div>
                        {/*  
                            Selezionatori riceve un array di oggetti
                        */}
                        <div className="Label">
                            <p>Selezionatori:</p>
                            {
                                this.state.experts ? (
                                    <span className="Select">
                                        <button type="button" onClick={this.addSelezionatore}>Aggiungi</button>
                                        <Select
                                            name="assists"
                                            label=""
                                            value={this.state.selezionatoreSelezionatoObj.value}
                                            placeholder="Cerca un selezionatore"
                                            handleChange={this.selectSelezionatore}
                                            selectOptions={this.state.expertsSelectDropDown}
                                            isSearchable
                                        />
                                    </span>
                                ) : (
                                        <span>
                                            Loading
                                        </span>
                                    )
                            }
                            <ul className="Lista">
                                {
                                    this.state.selezionatori.map(username =>
                                        <li>
                                            <div className="nomeCognome">{username}</div>
                                            <button type="button" onClick={() => this.removeSelezionatore(username)}>Rimuovi</button>
                                        </li>
                                    )
                                }
                            </ul>
                        </div>
                    </form>
                    <button onMouseDown={this.modify} className="button">Modifica</button>
                    <button onMouseDown={this.props.handleMouseDown} className="button">Annulla</button>
                </div>
            </div>
        )

    }
}

export default ModifyProjectForm