import axios from 'axios';

export function createProject(jwt, titolo, descrizione, requisiti) {
    let headers = {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${jwt}`
    }
    return axios.post('http://localhost:8080/api/progetti/creaNuovoProgetto', {
        'titolo': titolo,
        'descrizione': descrizione,
        'requisiti': requisiti
    }, { headers: headers })
}

export function getStorefront() {
    let config = {
        url: 'http://localhost:8080/api/progetti/storefront',
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
        }
    }
    return axios(config)
        .then(res => { return res.data })
        .catch(err => { return Promise.reject(err) })
}

export function getProjectByUsername(jwt, username) {
    let config = {
        url: 'http://localhost:8080/api/progetti/getProjectByUsername',
        method: 'post',
        data: {
            'username': username
        },
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }
    return axios(config)
        .then(res => { return res.data })
        .catch(err => { return Promise.reject(err) })
}

export function getProjectByProjectID(projectID) {

    let config = {
        url: 'http://localhost:8080/api/projects',
        method: 'get',
        headers: {
            'ProjectID': projectID,
            'Content-Type': 'application/json'
        }
    }

    return axios(config)
        .then(res => { return res.data })
        .catch(err => { return Promise.reject(err) })
}

export function getRecruitersProject(recruiterID) {

    let config = {
        url: 'http://localhost:8080/api/projects',
        method: 'get',
        headers: {
            'RecruiterID': recruiterID,
            'Content-Type': 'application/json'
        }
    }

    return axios(config)
        .then(res => { return res.data })
        .catch(err => { return Promise.reject(err) })
}

export function modifyProject(jwt, id, titolo, descrizione, requisiti, selezionatori) {
    let config = {
        url: 'http://localhost:8080/api/progetti/modify',
        method: 'post',
        data: {
            'id': id,
            'titolo': titolo,
            'descrizione': descrizione,
            'requisiti': requisiti,
            'selezionatori': selezionatori
        },
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }
    return axios(config)
}