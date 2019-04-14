package etf.unsa.ba.BookDetails.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.BookDetails.Entities.Author;
import etf.unsa.ba.BookDetails.Entities.Book;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

}
