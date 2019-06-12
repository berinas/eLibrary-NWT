import React, { Component } from 'react';
import { Nav, Navbar, Form, FormControl, Button} from 'react-bootstrap';
import styled from 'styled-components';
import './NavigationBar.css';


class NavigationBar extends Component {


    render(){
        return(
            <Navbar bg="dark" variant="dark" className="horizontal-menu">
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Item><Nav.Link href="/search/section/BOOK">BOOKS</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/search/section/ARTICLE">ARTICLES</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/search/section/MAGAZINE">MAGAZINES</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/users">USERS</Nav.Link></Nav.Item>
                </Nav>
                <Nav className="ml-auto">
                    <Nav.Item><Nav.Link href="/home" >LOG OUT</Nav.Link></Nav.Item>

                </Nav>
                <Nav>&nbsp;</Nav>
                <Form inline>
                <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                    <Button href="/search" variant="outline-info">GO</Button>
                </Form>
            </Navbar.Collapse>
        </Navbar>
        );
    }
}

export default NavigationBar;
