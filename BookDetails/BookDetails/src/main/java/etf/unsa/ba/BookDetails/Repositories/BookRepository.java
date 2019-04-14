package etf.unsa.ba.BookDetails.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.BookDetails.Entities.Book;


@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
	
	public Optional<Book> findById(Long id);
	public List<Book> findByTitle(String title);

}
