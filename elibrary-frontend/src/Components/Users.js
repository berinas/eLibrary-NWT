import React, { Component } from 'react';
import { CardGroup, Card, Button } from 'react-bootstrap';
import { Link } from 'react-router-dom';

import AdminNavigationBar  from './AdminNavigationBar';
import VerticalNavbar from './VerticalNavbar';

export default class Users extends Component {


    render() {
    return (
        <div><AdminNavigationBar />
            <VerticalNavbar />

            </div>
    );
    }
}
