package etf.unsa.ba.BookDetails.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import etf.unsa.ba.BookDetails.Entities.Category;
import etf.unsa.ba.BookDetails.Entities.Category.BookCategory;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	public Category findByCategory(BookCategory bookCategory);
}
