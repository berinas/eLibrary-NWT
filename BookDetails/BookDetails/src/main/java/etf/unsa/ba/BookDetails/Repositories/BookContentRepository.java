package etf.unsa.ba.BookDetails.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import etf.unsa.ba.BookDetails.Entities.BookContent;


@Repository
public interface BookContentRepository extends JpaRepository<BookContent, Long> {

}
