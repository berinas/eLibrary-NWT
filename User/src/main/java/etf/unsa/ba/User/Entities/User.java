package etf.unsa.ba.User.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id; 
	
	@NotNull
	@NotBlank(message = "First name is required")
	@Size(min=2, max=30, message="First name should have atleast 2 characters")
	@Column(name = "first_name")
	private String firstName; 
	
	@NotNull
	@NotBlank(message = "Last name is required")
	@Size(min=2, max=30, message="Last name should have atleast {min} characters")
	@Column(name = "last_name")
	private String lastName; 
	
	@NotNull
	@NotBlank(message = "Email is required")
	@Column(name = "email")
    private String email;
	
	@NotNull
	@NotBlank(message = "Username is required")
	@Size(min=4, max=20, message="Username should have at least {min} characters")
	@Column(unique = true)
	private String username;
	
	@NotNull
	@NotBlank(message = "Password is required.")
	@Size(min=8, max=100, message="Password should have at least {min} characters")
	private String password;	

	@ManyToOne(fetch=FetchType.LAZY)
	@JsonIgnore
	private UserRole userRole;
	private String role;
	
	
	protected User() {}
	
	public User(String firstName, String lastName, String email, String username, String password, String role) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username; 
		this.password = password; 
		this.role = role;
	}

	public Long getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	} 
	
	
}
