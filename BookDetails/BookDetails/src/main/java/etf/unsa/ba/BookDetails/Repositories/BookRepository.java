package etf.unsa.ba.BookDetails.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.BookDetails.Entities.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

}
