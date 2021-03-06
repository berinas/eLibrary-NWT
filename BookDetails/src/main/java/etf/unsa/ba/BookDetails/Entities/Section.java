package etf.unsa.ba.BookDetails.Entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Section {
	
	
	public enum SectionType {
		BOOK,
		MAGAZINE,
		ARTICLE
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	@Enumerated(EnumType.STRING)
    @Column(unique = true, length = 100)
	private SectionType section;
	

	@OneToMany(mappedBy="section")
	private List<Book> books; 
	
	protected Section() { } 
	
	public Section(SectionType section) {
		super();
		this.section = section;
	}

	public Integer getId() {
		return id;
	}
		
	public SectionType getSection() {
		return section;
	}

	public void setSection(SectionType section) {
		this.section = section;
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
