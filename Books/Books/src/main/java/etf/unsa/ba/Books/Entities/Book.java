package etf.unsa.ba.Books.Entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id; 
	private String title;
	
	@ManyToMany(mappedBy="booksWishList", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<User> usersWishList = new ArrayList<User>();
	
	
	@ManyToMany(mappedBy="booksReading", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<User> usersReading;
	
	
	@ManyToMany(mappedBy="booksRead", cascade = CascadeType.PERSIST)
	@JsonIgnore
	private List<User> usersRead;
	
	
	protected Book() {}
	
	public Book(String title) {
		super();
		this.title = title;
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

	public List<User> getUsersWishList() {
		return usersWishList;
	}

	public void setUsersWishList(List<User> usersWishList) {
		this.usersWishList = usersWishList;
	}

	public List<User> getUsersReading() {
		return usersReading;
	}

	public void setUsersReading(List<User> usersReading) {
		this.usersReading = usersReading;
	}

	public List<User> getUsersRead() {
		return usersRead;
	}

	public void setUsersRead(List<User> usersRead) {
		this.usersRead = usersRead;
	}

}
