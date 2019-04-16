package etf.unsa.ba.BookDetails.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import etf.unsa.ba.BookDetails.Entities.Section;
import etf.unsa.ba.BookDetails.Entities.Section.SectionType;


@Repository
public interface SectionRepository extends JpaRepository<Section, Long> {

	public Optional<Section> findBySection(SectionType section);
}
