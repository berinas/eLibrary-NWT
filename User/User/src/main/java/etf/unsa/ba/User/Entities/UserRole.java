package etf.unsa.ba.User.Entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class UserRole {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	private String role; 
	
	@OneToMany(mappedBy="userRole")
	private List<User> users;
	

	protected UserRole() {}

	
	public UserRole(String role) {
		super();
		this.role = role;
	}

	public Integer getId() {
		return id; 
	}
	
	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
	
	
	
}
