package etf.unsa.ba.BookDetails.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.BookDetails.Entities.Publisher;


@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {

	public Optional<Publisher> findByName(String name);
	
}
