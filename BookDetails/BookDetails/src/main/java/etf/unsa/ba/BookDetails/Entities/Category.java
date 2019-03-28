package etf.unsa.ba.BookDetails.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Category {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String category;
	

	@OneToMany(mappedBy="section")
	private List<Section> sections; 
	
	protected Category() {}
	

}
