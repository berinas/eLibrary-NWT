package etf.unsa.ba.Books.Entities;

public class Publisher {
	
	public Long id; 
	public String name;
		
	public Publisher(Long id,String name) {
		super();
		this.id = id; 
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


	
	
}
