import axios from 'axios';

export function getExperts() {

    let config = {
        url: 'http://localhost:8080/api/experts',
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        }
    }

    return axios(config)
        .then(res => { return res.data })
        .catch(err => { return Promise.reject(err) })
}