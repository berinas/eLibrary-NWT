package etf.unsa.ba.BookDetails.Entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


//@JsonIgnoreProperties(ignoreUnknown = true)
@Entity
@Table(name = "BOOKS")
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title; 
	private String description; 
	private String language; 
	private Date published; 
	

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "content_id", referencedColumnName = "id")
	private BookContent bookContent; 
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Publisher publisher;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Section section;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Category category;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="BOOK_AUTHOR", joinColumns = {@JoinColumn(name="BOOK_ID")}, inverseJoinColumns= {@JoinColumn(name="AUTHOR_ID")})
	private List<Author> authors;
	
	protected Book() { }
	
	public Book(String title, String description, String language, Date published) {
		super();
		this.title = title;
		this.description = description;
		this.language = language;
		this.published = published;
		//this.authors = (List<Author>) Stream.of(authors).collect(Collectors.toSet());
        //this.authors.forEach(x -> x.getBooks().add(this));
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getPublished() {
		return published;
	}

	public void setPublished(Date published) {
		this.published = published;
	}
	
	public BookContent getBookContent() {
		return bookContent;
	}

	public void setBookContent(BookContent bookContent) {
		this.bookContent = bookContent;
	}
	
	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
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

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	} 
	
	public void addAuthor(Author author) {
		this.authors.add(author);
	}
}
