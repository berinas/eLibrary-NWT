package etf.unsa.ba.BookDetails.Entities;

import java.util.ArrayList;
import java.util.List;


public class BookDetailsRequestBody {
	
	Book book; 
	List<Author> authors = new ArrayList<Author>(); 
	Section section; 
	Category category; 
	Publisher publisher;
	
	
	
	public BookDetailsRequestBody(Book book, List<Author> authors, Section section, Category category,
			Publisher publisher) {
		super();
		this.book = book;
		this.authors = authors;
		this.section = section;
		this.category = category;
		this.publisher = publisher;
	}
	
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public List<Author> getAuthors() {
		return authors;
	}
	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}
	public Section getSection() {
		return section;
	}
	public void setSection(Section section) {
		this.section = section;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public Publisher getPublisher() {
		return publisher;
	}
	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}
	
	
}
