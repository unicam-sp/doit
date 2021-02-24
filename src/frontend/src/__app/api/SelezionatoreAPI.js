import axios from 'axios'

export function getUsernameById(jwt, id) {
    let config = {
        url: 'http://localhost:8080/api/selezionatore/getUsernameById',
        method: 'post',
        data: {
            'id': id
        },
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }
    return axios(config)
}