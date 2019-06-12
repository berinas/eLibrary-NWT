import React, { Component } from 'react';
import { Nav, Navbar, Form, FormControl, Button} from 'react-bootstrap';
import styled from 'styled-components';
import './VerticalNavbar.css';


class VerticalNavbar extends Component {


    render(){
        return(
            <Nav defaultActiveKey="/home" className="flex-column">
                <Nav.Link href="/search/category/SCIENCE" className="iteem">SCIENCE</Nav.Link>
                <Nav.Link href="/search/category/HISTORY" className="iteem">HISTORY</Nav.Link>
                <Nav.Link href="/search/category/MEDICINE" className="iteem">MEDICINE</Nav.Link>
                <Nav.Link href="/search/category/FANTASY>" className="iteem">FANTASY</Nav.Link>
                <Nav.Link href="/search/category/ART" className="iteem">ART</Nav.Link>
                <Nav.Link href="/search/category/ROMANCE" className="iteem">ROMANCE</Nav.Link>
                <Nav.Link href="/search/category/MUSIC" className="iteem">MUSIC</Nav.Link>
                <Nav.Link href="/search/category/CHILDREN" className="iteem">CHILDREN</Nav.Link>
                <Nav.Link href="/search/category/RELIGION" className="iteem">RELIGION</Nav.Link>
            </Nav>
        );
    }
}

export default VerticalNavbar;
