import React, { Component } from "react"
import { Redirect } from 'react-router-dom'
import Form from "../components/Form/Form";
import FlashMessage from "../components/FlashMessage/FlashMessage";
import { getAllProperties } from "../Utils";
import { postLogin } from "../api/AuthAPI";

class Login extends Component {

    constructor() {
        super();

        this.state = {
            username: 'None',
            password: 'None',
            error: '',
            message: '',
            login_flag: false
        }

        this.handleSubmit = this.handleSubmit.bind(this)
        this.handleChangeUsername = this.handleChangeUsername.bind(this)
        this.handleChangePassword = this.handleChangePassword.bind(this)
    }

    componentDidMount() {
        if (this.props.location.state) {
            this.setState({
                message: this.props.location.state.message
            })
        }
    }

    handleSubmit() {
        postLogin(this.state.username, this.state.password)
            .then(res => {
                if (res) {
                    if (res.status === 200) {
                        if (res.data) {
                            this.setState({
                                login_flag: true,
                                error: ''
                            })
                            this.props.setJwt(res.data) // salvataggio del jwt in memoria
                        }
                    }
                }
            })
            .catch(err => {
                getAllProperties(err)
                if (err.response) {
                    if (err.response.status === 401) {
                        this.setState({ error: err.response.data })
                    }
                }
                else {
                    this.setState({ error: "Il server non è raggiungibile" })
                }
            })
    }

    handleChangeUsername(event) {
        this.setState({
            username: event.target.value
        })
    }

    handleChangePassword(event) {
        this.setState({
            password: event.target.value
        })
    }

    render() {

        if (this.state.login_flag) return <Redirect to={{
            pathname: "/",
            state: { message: "autenticazione avvenuta con successo" }
        }} />

        let usernameError = ''
        let passwordError = ''

        if (this.state.username === '') {
            usernameError = 'Inserire un nome utente'
        }
        if (this.state.password === '') {
            passwordError = 'Inserire una password'
        }

        const inputs = [{
            id: 'username',
            name: 'username',
            placeholder: 'username',
            type: 'text',
            error: usernameError,
            handleChange: this.handleChangeUsername
        }, {
            id: 'password',
            name: 'password',
            placeholder: 'password',
            type: 'password',
            error: passwordError,
            handleChange: this.handleChangePassword
        }, {
            type: 'button',
            value: 'Log in',
            onClick: this.handleSubmit
        }]

        const props = {
            inputs: inputs,
            style: { width: '40%' },
        }

        return (
            <div className='contentStyles' >
                <h1>Login</h1>
                <div className='FormContainer'>
                    <div>
                        <Form {...props} />
                    </div>
                </div>
                <FlashMessage msg_from_parent={this.state.message} class={'msg'} />
                <FlashMessage msg_from_parent={this.state.error} class={'error'} />
            </div >
        );
    }
}

/*
    <Form {...props} error={this.state.error} />

    <div>
        <form method="post" action="/login">
            <input type="text" id="username" name="username" placeholder="Username" />
            <input type="password" id="password" name="password" placeholder="Password" />
            <button type="submit">Sign in</button>
        </form>
    </div>
*/
export default Login;