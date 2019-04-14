package etf.unsa.ba.User.Entities;

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
public class UserRole {

    public enum Role {
        ADMIN,
        USER
    }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id; 
	@Enumerated(EnumType.STRING)
    @Column(unique = true, length = 100)
	private Role role; 
	
	@OneToMany(mappedBy="userRole")
	private List<User> users;
	

	protected UserRole() {}

	
	public UserRole(Role role) {
		super();
		this.role = role;
	}

	public Integer getId() {
		return id; 
	}
	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}
	
}
