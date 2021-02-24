
export function getAllProperties(obj) {
    console.log(" ### All The Props ### ")
    let table = []
    let props = Object.getOwnPropertyNames(obj)
    props.forEach((prop, index) => {
        table[index] = {
            'name': props[index],
            'enumerable': obj.propertyIsEnumerable(prop)
        }
    })
    console.table(table)
}

export function getEnumProperties(obj) {
    for (let prop in obj) {
        console.log(prop)
        console.log(obj[prop])
    }
}

export function errHandling(err) {
    if (err.response) {
        if (err.response.status === 400) {
            console.log(err.response)
            return `400: richiesta non riuscita`
        }
        if (err.response.status === 401)
            return '401: credenziali non autorizzate'
        if (err.response.status === 403)
            return '403: credenziali non riconosciute o mancanti'
        else {
            getAllProperties(err)
            console.log(err.message)
            console.log(err.response)
            return "errore sconosciuto"
        }
    }
    if (err.message) {
        return err.message
    }
    else return ''
}

/*
    Convert Base64 string into a blob
*/
export function dataURItoBlob(dataURI, contentType) {
    // decodifica il base64
    const byteCharacters = atob(dataURI)
    // creo un array con il valore ASCII di ogni carattere
    const byteNumbers = new Array(byteCharacters.length)
    for (let i = 0; i < byteCharacters.length; i++) {
        byteNumbers[i] = byteCharacters.charCodeAt(i)
    }
    // creo un array che contiene i byte di 
    // ogni valore ASCII
    const byteArray = new Uint8Array(byteNumbers)
    return new Blob([byteArray], {type: contentType});
}

export function isProgettista(decoded) {
    console.log(decoded)
    if(decoded === '' || decoded === null) return false
    let roles = decoded.roles
    for(let role in roles) {
        if(roles[role].authority === "PROPOSITORE") return true
    }
    return false
}

export function isEsperto(decoded) {
    if(decoded === '' || decoded === null) return false
    let roles = decoded.roles
    for(let role in roles) {
        if(roles[role].authority === "ESPERTO") return true
    }
    return false
}