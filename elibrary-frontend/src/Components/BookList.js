import React, { Component } from 'react';
import { CardGroup, Card, Button } from 'react-bootstrap'; 
import { Link } from 'react-router-dom';

class BookList extends Component {

  constructor(props) {
    super(props);
    this.state = {books: [], isLoading: true}
    //this.remove = this.remove.bind(this);
  };

  componentDidMount() {
    this.setState({isLoading: true});

    fetch('/book-details-service/books/' + this.props.match.params.type + '/' + this.props.match.params.name)
      .then(response => response.json())
      .then(data => this.setState({books: Array.from(data), isLoading: false}));
  }

  /*
  async remove(id) {
    await fetch(`/books/${id}`, {
      method: 'DELETE',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      }
    }).then(() => {
      let updatedBooks = [...this.state.books].filter(i => i.id !== id);
      this.setState({books: updatedBooks});
    });
  } */

  render(){
      return(
              <CardGroup>
                  {
                      this.state.books.map((book) => {
                          return (
                                <Card key={book.id}>
                                    <Card.Img variant="top" src={book.img}/>
                                    <Card.Body>
                                    <Card.Title>{book.title}</Card.Title>
                                    <Card.Text>
                                        {book.description}
                                    </Card.Text>
                                    <Card.Link href="#">See more</Card.Link>
                                    </Card.Body>
                                    <Card.Footer>
                                    <small className="text-muted">Rating</small>
                                    </Card.Footer>
                                </Card>
                            )
                      })
                  }
              </CardGroup> 
      );
  }



}

export default BookList;