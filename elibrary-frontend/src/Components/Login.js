import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Bootstrap from "react-bootstrap";
import './Login.css';

export default class Login extends Component {
    constructor(props) {
    super(props);

    this.state = {
        username: "",
        password: ""
    };
    }

    validateForm() {
    return this.state.username.length > 0 && this.state.password.length > 0;
    }

    handleChange = event => {
    this.setState({
        [event.target.id]: event.target.value
    });
    }

    handleSubmit = event => {
    event.preventDefault();
    }

    render() {
    return (

            <Form onSubmit={this.handleSubmit} className="login-form">
            <Form.Group controlId="formBasicUsername" bsSize="large">
                <Form.Label>Username</Form.Label>
                <Form.Control
                autoFocus
                type="text"
                value={this.state.username}
                onChange={this.handleChange}
            />
            </Form.Group>

            <Form.Group controlId="formBasicPassword">
                <Form.Label >Password</Form.Label>
                <Form.Control
                value={this.state.password}
                onChange={this.handleChange}
                type="password"
            />
            </Form.Group>
            <Button className="loginBtn" variant="success" type="submit" disabled={!this.validateForm()}>
                LOG IN
            </Button>
            </Form>
    );
    }
}
