package etf.unsa.ba.BookDetails.Entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class BookContent {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	
	@OneToOne(mappedBy = "bookContent")
	@JsonIgnore
	private Book book;
	
	protected BookContent(){}

	public BookContent(Book book) {
		this.book = book;
	}
	
	public Long getId() {
		return id;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}
}
