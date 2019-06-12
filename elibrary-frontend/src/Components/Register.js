import React, { Component } from "react";
import Form from 'react-bootstrap/Form'
import Button from 'react-bootstrap/Button'
import Bootstrap from "react-bootstrap";
import './Register.css'

import NavigationBar  from './NavigationBar';
import VerticalNavbar from './VerticalNavbar';

export default class Login extends Component {
    constructor(props) {
    super(props);

    this.state = {
        firstName:"",
        lastName: "",
        username: "",
        email: "",
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
      <div> <NavigationBar />
            <VerticalNavbar />
            <Form onSubmit={this.handleSubmit} className="register-form">
                 <Form.Group controlId="formBasicFirstName" bsSize="large">
                    <Form.Label>First name</Form.Label>
                    <Form.Control
                    autoFocus
                    type="text"
                    value={this.state.firstName}
                    onChange={this.handleChange}
                />
                </Form.Group>
                 <Form.Group controlId="formBasicLastName" bsSize="large">
                    <Form.Label>Last name</Form.Label>
                    <Form.Control
                    autoFocus
                    type="text"
                    value={this.state.lastName}
                    onChange={this.handleChange}
                />
                </Form.Group>
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
                <Form.Label>Password</Form.Label>
                <Form.Control
                value={this.state.password}
                onChange={this.handleChange}
                type="password"
                 />
                 <Form.Text className="text-muted">
                 Password must contain at least 6 characters.
                 </Form.Text>
                </Form.Group>
                <Form.Group controlId="formBasicConfirmPassword">
                <Form.Label>Confirm password</Form.Label>
                <Form.Control
                onChange={this.handleChange}
                type="password"
                 />
                </Form.Group>

            <Button className="registerBtn" variant="danger" type="submit" disabled={!this.validateForm()}>
                REGISTER
            </Button>
            </Form>
      </div>
    );
    }
}
