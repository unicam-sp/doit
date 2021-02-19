import axios from 'axios';
// import { getAllProperties, getEnumProperties } from "../Utils";

export function getExperts() {

    let config = {
        url: 'http://localhost:8080/api/experts',
        method: 'get',
        headers: {
            'Content-Type': 'application/json'
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