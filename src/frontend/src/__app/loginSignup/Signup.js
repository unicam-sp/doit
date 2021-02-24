import React, { Component } from "react";
import { Redirect } from 'react-router-dom'
import Form from "../components/Form/Form";
import { postSignUpPersona, postSignUpEnte } from "../api/AuthAPI";
import { errHandling } from "../Utils";

class Signin extends Component {

    constructor() {
        super();

        this.state = {
            registration_flag: false,
            error: '',
            username: 'None',
            password: 'None',
            confimrPassword: 'None',
            email: 'None',

            nomeEnte: 'None',
            VATNumber: 'None',

            nome: 'None',
            cognome: 'None',

            enteFormVisibility: false,
            personaFormVisibility: false,
        }

        this.registrazionePersona = this.registrazionePersona.bind(this)
        this.registrazioneEnte = this.registrazioneEnte.bind(this)
        this.handleChange = this.handleChange.bind(this)

        this.handleEnteFormVisibility = this.handleEnteFormVisibility.bind(this)
        this.handlePersonaFormVisibility = this.handlePersonaFormVisibility.bind(this)
    }

    handleChange = (event) => {
        const { id, value } = event.target;
        this.setState({
            [id]: value
        })
    }

    handleEnteFormVisibility() {
        this.setState({
            enteFormVisibility: true,
            personaFormVisibility: false
        })
    }

    handlePersonaFormVisibility() {
        this.setState({
            enteFormVisibility: false,
            personaFormVisibility: true
        })
    }

    // ---------------------------
    // persona
    registrazionePersona() {
        postSignUpPersona(
            this.state.username,
            this.state.password,
            this.state.email,
            this.state.nome,
            this.state.cognome
        )
        .then(res => {
            if (res.status === 201 && res.data === 'Nuovo utente creato!')
                this.setState({
                    registration_flag: true
                })
        })
        .catch(err => { this.setState({ error: errHandling(err) }) })
    }

    // ---------------------------
    // ente
    registrazioneEnte() {
        postSignUpEnte(
            this.state.username,
            this.state.password,
            this.state.email,
            this.state.nomeEnte,
            this.state.VATNumber
        )
        .then(res => {
            console.log(res)
        })
        .catch(err => { this.setState({ error: errHandling(err) }) })
    }

    render() {

        if (this.state.registration_flag) return <Redirect to={{
            pathname: "/login",
            state: { message: "registrazione avvenuta con successo" }
        }} />

        let usernameError = ''
        let passwordError = ''
        let confirmPasswordError = ''
        let emailError = ''

        let enteNomeError = ''
        let VATNumberError = ''

        let nomeError = ''
        let cognomeError = ''

        // user
        if (this.state.username === '') {
            usernameError = 'non può essere vuoto'
        }
        if (this.state.password === '') {
            passwordError = 'non può essere vuoto'
        }
        if (this.state.email === '') {
            emailError = 'non può essere vuoto'
        }
        if (this.state.password !== this.state.confimrPassword) {
            confirmPasswordError = 'le password devono essere uguali'
        }

        // ente
        if (this.state.nomeEnte === '') {
            enteNomeError = 'non può essere vuoto'
        }
        if (this.state.VATNumber === '') {
            VATNumberError = 'non può essere vuoto'
        }

        // persona
        if (this.state.nome === '') {
            nomeError = 'non può essere vuoto'
        }
        if (this.state.cognome === '') {
            cognomeError = 'non può essere vuoto'
        }

        // ---------------------------
        // username, password, email
        // ---------------------------
        const inputsIdPass = [{
            id: 'username',
            placeholder: 'username',
            type: 'text',
            error: usernameError,
            handleChange: this.handleChange
        }, {
            id: 'password',
            placeholder: 'password',
            type: 'password',
            error: passwordError,
            handleChange: this.handleChange
        },
        {
            id: 'confimrPassword',
            placeholder: 'conferma password',
            type: 'password',
            error: confirmPasswordError,
            handleChange: this.handleChange
        }, {
            id: 'email',
            placeholder: 'email',
            type: 'text',
            error: emailError,
            handleChange: this.handleChange
        }]

        const proposIdPass = {
            inputs: inputsIdPass,
            style: { width: '40%' },
        }

        // ---------------------------
        // ente
        const inputsEnte = [{
            id: 'nomeEnte',
            placeholder: this.state.nomeEnte === 'None' ? 'nome organizzazione' : this.state.nomeEnte,
            type: 'text',
            error: enteNomeError,
            handleChange: this.handleChange
        }, {
            id: 'VATNumber',
            placeholder: this.state.VATNumber === 'None' ? 'numero VAT/IRC' : this.state.VATNumber,
            type: 'text',
            error: VATNumberError,
            handleChange: this.handleChange
        }, {
            type: 'button',
            value: 'Registra Ente',
            className: 'button',
            onClick: this.registrazioneEnte
        }]

        const propsEnte = {
            inputs: inputsEnte,
            style: { width: '40%' },
        }

        // ---------------------------
        // persona
        // ---------------------------
        const inputsPersona = [{
            id: 'nome',
            placeholder: this.state.nome === 'None' ? 'nome' : this.state.nome,
            type: 'text',
            error: nomeError,
            handleChange: this.handleChange
        }, {
            id: 'cognome',
            placeholder: this.state.cognome === 'None' ? 'cognome' : this.state.cognome,
            type: 'text',
            error: cognomeError,
            handleChange: this.handleChange
        }, {
            type: 'button',
            value: 'Registra Persona',
            onClick: this.registrazionePersona
        }]

        const propsPersona = {
            inputs: inputsPersona,
            style: { width: '40%' },
        }

        return (
            <div className="contentStyles" >
                <h1>Registrazione</h1>
                {this.state.post_error && <div className="error">{this.state.post_error}</div>}
                <Form {...proposIdPass} error='' />
                <button style={style} onClick={this.handleEnteFormVisibility}>Ente</button>
                <button style={style} onClick={this.handlePersonaFormVisibility}>Persona</button>

                {this.state.enteFormVisibility && <Form {...propsEnte} error={this.state.error} />}

                {this.state.personaFormVisibility && <Form {...propsPersona} error={this.state.error} />}
            </div>
        )
    }
}

const style = {
    margin: '10px',
    padding: '3px',
    outline: 'none',
    borderStyle: 'none'
}

export default Signin;