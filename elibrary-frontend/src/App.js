import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import { About } from './About';
import { NotFound } from './NotFound';
import { Layout } from './Components/Layout';
import NavigationBar  from './Components/NavigationBar';
import VerticalNavbar from './Components/VerticalNavbar';
import Home  from './Components/Home';
import Login from './Components/Login';
import Register from './Components/Register';

import BookList  from './Components/BookList';

function App() {
  return (
      <React.Fragment>
        <NavigationBar />

        <VerticalNavbar />
        <Layout>
          <Router>
            <Switch>
              <Route path ="/search/:type/:name" component={BookList} />
              <Route path ="/about" component={About} />
              <Route path ="/home" component={Home}/>
              <Route path ="/login" component={Login} />
              <Route path ="/register" component={Register} />
              <Route exact path ="/" component={Home}/>
              
              <Route component={NotFound} />
            </Switch>
          </Router>
        </Layout>
      </React.Fragment>
  );
}

export default App;
