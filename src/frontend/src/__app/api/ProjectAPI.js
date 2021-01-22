import axios from 'axios';

export function getProjectByUserID(userID) {

    let config = {
        url: 'http://localhost:8080/api/projects',
        method: 'get',
        headers: {
            'UserID': userID,
            'Content-Type': 'application/json'
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