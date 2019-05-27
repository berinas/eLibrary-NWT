package etf.unsa.ba.BookDetails.Entities;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	public enum BookCategory{
		
		SCIENCE,
		HISTORY,
		MEDICINE,
		FANTASY,
		ART,
		ROMANCE,
		MUSIC,
		CHILDREN,
		RELIGION
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
    @Column(unique = true, length = 100)
	private BookCategory category;
	
	@OneToMany(mappedBy="category")
	private List<Book> books; 
	
	
	protected Category() {}
	
	public Category(BookCategory category, Book ...books) {
		this.category = category;
		this.books = Stream.of(books).collect(Collectors.toList());
        this.books.forEach(x -> x.setCategory(this));
	}

	public BookCategory getCategory() {
		return category;
	}

	public void setCategory(BookCategory category) {
		this.category = category;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
}
