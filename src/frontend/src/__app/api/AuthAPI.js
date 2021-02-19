import axios from 'axios'

export function postLogin(username, password) {
    console.log("login")
    let headers = {
        'Content-Type': 'application/json' 
    }
    return axios.post('http://localhost:8080/api/auth/login', {
        'username': username,
        'password': password
    }, headers)
}

export function postSignUpPersona(username, password, email, nome, cognome) {
    let headers = {
        'Content-Type': 'application/json' 
    }
    return axios.post('http://localhost:8080/api/auth/signUpPersona', {
        'username': username,
        'password': password,
        'email': email,
        'nome': nome,
        'cognome': cognome
    }, headers)
}

export function postSignUpEnte(username, password, email, nomeEnte, VATNumber) {
    let headers = {
        'Content-Type': 'application/json' 
    }
    return axios.post('http://localhost:8080/api/auth/signUpEnte', {
        'username': username,
        'password': password,
        'email': email,
        'nomeEnte': nomeEnte,
        'VATNumber': VATNumber
    }, headers)
}