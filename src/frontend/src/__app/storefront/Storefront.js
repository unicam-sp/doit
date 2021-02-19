import React, { Component } from 'react'
import '../General.css';
import { getStorefront } from "../api/ProjectAPI";

class Storefront extends Component {

    constructor() {
        super();

        this.state = {
            prova: ''
        }
    }

    componentDidMount() {
        getStorefront()
        .then(res => {
            console.log(res);
        })
        .catch(err => {
            console.log(err);
        })
    }

    render() {
        return (
            <div className="contentStyles">
                <h1>Vetrina</h1>
            </div>
        )
    }
}

export default Storefront;
