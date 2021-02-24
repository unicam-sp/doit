import axios from 'axios'

export function getExperts(jwt) {
    let config = {
        url: 'http://localhost:8080/api/expert/',
        method: 'get',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': `Bearer ${jwt}`
        }
    }
    return axios(config)
}

/*
return axios({
        method: 'post',
        url: 'http://localhost:8080/api/user/loadCv',
        headers: {
            'Content-Type': 'multipart/form-data',
            'Authorization': 'Bearer ' + jwt
        },
        formData: formData
    })
*/

/*
    axios.post(url, {
        //...data
      }, {
        headers: {
          'Authorization': `Bearer ${token}`
        }
      })
*/