import axios from 'axios'

export function getAll() {
    console.log("login")
    let headers = {
        'Content-Type': 'application/json' 
    }
    return axios.get('http://localhost:8080/api/auth/login', {
        'username': username,
        'password': password
    }, headers)
}
