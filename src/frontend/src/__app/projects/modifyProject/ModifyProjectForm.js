import React, { Component } from "react"
import Select from '../../components/Select'
import { getExperts } from "../../api/UserAPI";
import "../Project.css"

class ModifyProjectForm extends Component {

    constructor(props) {
        super(props)

        this.state = {
            id: props.projectSelected.id,
            selezionatori: props.projectSelected.selezionatori,
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

        this.handleChange = this.handleChange.bind(this)
        this.removeSelezionatore = this.removeSelezionatore.bind(this)
        this.selectSelezionatore = this.selectSelezionatore.bind(this)
        this.addSelezionatore = this.addSelezionatore.bind(this)
    }

    componentDidMount() {
        getExperts()
            .then(arr => {
                let output = []
                arr.forEach((obj, index) => {
                    console.log(obj.nome)
                })
                arr.forEach(obj => {
                    output.push({
                        key: obj.id,
                        value: obj.nome + " " + obj.cognome + " (" + obj.email + ")"
                    })
                })
                this.setState({
                    experts: arr,
                    expertsSelectDropDown: output
                })
            })
            .catch(err => this.setState({ errorMessage: err.message }))
    }

    /*
        Aggiorna il componente ogni volta che ritorna true.
        In questo caso aggiorna il componente ogni volta che uno stato cambia.
    */
    shouldComponentUpdate(nextProps) {
        if (this.state.id !== nextProps.projectSelected.id) {
            this.setState({
                id: nextProps.projectSelected.id,
                selezionatori: nextProps.projectSelected.selezionatori,
                titolo: nextProps.projectSelected.titolo,
                descrizione: nextProps.projectSelected.descrizione,
                requisiti: nextProps.projectSelected.requisiti,
            })
        }
        return true
    }

    handleChange(event) {
        event.preventDefault();
        this.setState({
            titolo: event.target.titolo,
            descrizione: event.target.descrizione,
            requisiti: event.target.requisiti,
        })
    }

    selectSelezionatore(event) {
        console.log(event);
        this.setState({
            selezionatoreSelezionatoObj: event
        })
    }

    addSelezionatore() {
        let id = this.state.selezionatoreSelezionatoObj.key
        let selezionatore = this.state.experts.find(obj => { return obj.id === id })
        this.setState({
            selezionatori: this.state.selezionatori.concat(selezionatore)
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

        console.log("re-rendering")
        var visibility = "hide";

        if (this.props.menuVisibility) {
            // mostra la form per la modifica
            visibility = "show";
        }

        return (
            <div id="FormBackground"
                className={visibility}>
                <div className="FormContainer">
                    <form className="Form">
                        <div className="Label">
                            <p>Titolo progetto:</p>
                            <textarea
                                type='text'
                                value={this.state.titolo}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="Label">
                            <p>Descrizione:</p>
                            <textarea
                                className="Descrizione"
                                type='text'
                                value={this.state.descrizione}
                                onChange={this.handleChange}
                            />
                        </div>
                        <div className="Label">
                            <p>Requisiti:</p>
                            <textarea
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
                            <ul>
                                <li>
                                    {
                                        this.state.experts ? (
                                            <span>
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
                                </li>
                                {
                                    this.state.selezionatori.forEach(sel => {
                                        <li>
                                            <div className="nomeCognome">{sel.nome} {sel.cognome}</div>
                                            <button type="button" onClick={() => this.removeSelezionatore(sel)}>Rimuovi</button>
                                        </li>
                                    })
                                }
                            </ul>
                        </div>
                    </form>
                    <button onMouseDown={this.props.handleMouseDown} className="buttonModificaEAnnulla">Modifica</button>
                    <button onMouseDown={this.props.handleMouseDown} className="buttonModificaEAnnulla">Annulla</button>
                </div>
            </div>
        )

    }
}

export default ModifyProjectForm