package etf.unsa.ba.Books.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.Books.Entities.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
