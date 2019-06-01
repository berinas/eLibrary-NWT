import React, { Component } from 'react';
import { Nav, Navbar, Form, FormControl, Button} from 'react-bootstrap'; 
import styled from 'styled-components';


class VerticalNavbar extends Component {
   

    render(){
        return( 
            <Nav defaultActiveKey="/home" className="flex-column">
                <Nav.Link href="/search/category/SCIENCE">Science</Nav.Link>
                <Nav.Link href="/search/category/HISTORY">History</Nav.Link>
                <Nav.Link href="/search/category/MEDICINE">Medicine</Nav.Link>
                <Nav.Link href="/search/category/FANTASY>">Fantasy</Nav.Link>
                <Nav.Link href="/search/category/ART">Art</Nav.Link>
                <Nav.Link href="/search/category/ROMANCE">Romance</Nav.Link>
                <Nav.Link href="/search/category/MUSIC">Music</Nav.Link>
                <Nav.Link href="/search/category/CHILDREN">Children</Nav.Link>
                <Nav.Link href="/search/category/RELIGION">Religion</Nav.Link>
            </Nav>
        );
    }
}

export default VerticalNavbar;