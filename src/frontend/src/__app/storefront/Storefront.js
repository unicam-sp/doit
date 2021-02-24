import React, { Component } from 'react'
import '../General.css';
import { getStorefront } from "../api/ProjectAPI";
import { errHandling } from "../Utils";

class Storefront extends Component {

    constructor() {
        super();

        this.state = {
            prova: '',
            error: ''
        }
    }

    componentDidMount() {
        getStorefront()
        .then(res => { console.log(res) })
        .catch(err => { this.setState({ error: errHandling(err) }) })
    }

    render() {
        return (
            <div className="contentStyles">
                <h1>Vetrina di tutti i progetti completati</h1>
                {this.state.error && <div className="error">{this.state.error}</div>}
            </div>
        )
    }
}

export default Storefront;
