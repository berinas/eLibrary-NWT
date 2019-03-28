package etf.unsa.ba.BookDetails.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Section {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String section;
	

	@OneToMany(mappedBy="section")
	private List<Book> books; 
	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private Category category;
	
	protected Section() { } 
	
	public Section(String section) {
		super();
		this.section = section;
	}

	public Integer getId() {
		return id;
	}
		
	public String getSection() {
		return section;
	}

	public void setSection(String section) {
		this.section = section;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	

}
