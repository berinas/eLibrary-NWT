import React, { Component } from 'react';
import { Nav, Navbar, Form, FormControl, Button} from 'react-bootstrap'; 
import styled from 'styled-components';


class NavigationBar extends Component {
   

    render(){
        return( 
            <Navbar bg="dark" variant="dark">
            <Navbar.Toggle aria-controls="basic-navbar-nav"/>
            <Navbar.Collapse id="basic-navbar-nav">
                <Nav className="mr-auto">
                    <Nav.Item><Nav.Link href="/search/section/BOOK">Books</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/search/section/ARTICLE">Articles</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/search/section/MAGAZINE">Magazines</Nav.Link></Nav.Item>
                </Nav>
                <Nav className="ml-auto">
                    <Nav.Item><Nav.Link href="/login">LOGIN</Nav.Link></Nav.Item>
                    <Nav.Item><Nav.Link href="/register">REGISTER</Nav.Link></Nav.Item>
                </Nav>
                <Form inline>
                <FormControl type="text" placeholder="Search" className="mr-sm-2" />
                    <Button href="/search" variant="outline-info">Search</Button>
                </Form>
            </Navbar.Collapse>
        </Navbar>
        );
    }
}

export default NavigationBar;