package etf.unsa.ba.Books.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	private String username;

	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="USER_BOOK_WISHLIST", joinColumns = {@JoinColumn(name="USER_ID")}, inverseJoinColumns= {@JoinColumn(name="BOOK_ID")})
	private List<Book> booksWishList;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="USER_BOOK_READING", joinColumns = {@JoinColumn(name="USER_ID")}, inverseJoinColumns= {@JoinColumn(name="BOOK_ID")})
	private List<Book> booksReading;
	
	@ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="USER_BOOK_READ", joinColumns = {@JoinColumn(name="USER_ID")}, inverseJoinColumns= {@JoinColumn(name="BOOK_ID")})
	private List<Book> booksRead;
	
	
	
	protected User() {
		
	}
	
	public User(String username) {
		super();
		this.username = username;
	}

	public Long getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}

	public List<Book> getBooksWishList() {
		return booksWishList;
	}

	public void setBooksWishList(List<Book> booksWishList) {
		this.booksWishList = booksWishList;
	}

	public List<Book> getBooksReading() {
		return booksReading;
	}

	public void setBooksReading(List<Book> booksReading) {
		this.booksReading = booksReading;
	}

	public List<Book> getBooksRead() {
		return booksRead;
	}

	public void setBooksRead(List<Book> booksRead) {
		this.booksRead = booksRead;
	}
	
	
	
}
