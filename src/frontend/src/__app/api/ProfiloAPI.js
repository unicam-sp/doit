import axios from 'axios';
import { getAllProperties, getEnumProperties } from "../Utils";

export function loadFile(jwt, file) {
    console.log(file)
    const formData = new FormData()
    formData.append('file', file)
    formData.append('title', file.name)

    let url = 'http://localhost:8080/api/profilo/loadCv'
    axios.post(url, formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
            'Authorization': `Bearer ${jwt}`
        }
    })
        .then(res => console.log(res))
        .catch(err => {
            getAllProperties(err)
            getEnumProperties(err)
        })
}

export function getUsernamesWithProfile(jwt) {
    let url = 'http://localhost:8080/api/profilo/profili'
    return axios.get(url, {
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    })
}

export function getProfileOf(jwt, username) {
    let url = 'http://localhost:8080/api/profilo/getWithUsername'
    let headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwt}`
    }
    return axios.post(url, {
        'username': username,
    }, {
        headers: headers
    })
}