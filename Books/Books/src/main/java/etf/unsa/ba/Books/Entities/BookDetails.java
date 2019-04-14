package etf.unsa.ba.Books.Entities;

import java.util.Date;

public class BookDetails {

	private Long id;
	private String title; 
	private String description; 
	private String language; 
	private Date published; 
	

	protected BookDetails() { }
	
	public BookDetails(String title, String description, String language, Date published) {
		super();
		this.title = title;
		this.description = description;
		this.language = language;
		this.published = published;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
	
}
