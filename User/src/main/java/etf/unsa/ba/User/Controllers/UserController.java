package etf.unsa.ba.User.Controllers;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.netflix.discovery.DiscoveryClient;

import etf.unsa.ba.User.Entities.User;
import etf.unsa.ba.User.Entities.UserAccount;
import etf.unsa.ba.User.Services.UserService;

@RestController	
@CrossOrigin
@RequestMapping(path= "/users")

public class UserController {

	@Autowired
    private UserService userService;
		
	
	public UserController(UserService userService){
        this.userService = userService;
    }

	
	@GetMapping()
    public List<User> getAllUsers() {
    	return userService.findAll();
    }
	
	@GetMapping("/{userId}")
	public User getUserById(@PathVariable("userId") Long userId){
	     return userService.findUserById(userId);
	} 
	
	@GetMapping("/first-name/{firstName}")
	public Iterable<User> getUsersByFirstName(@PathVariable("firstName") String firstName){
		return userService.findUsersByFirstName(firstName);
	}
	
	@GetMapping("/last-name/{lastName}")
	public Iterable<User> getUsersByLastName(@PathVariable("lastName") String lastName){
		return userService.findUsersByLastName(lastName);
	}
	
	@GetMapping("/email/{email}")
	public User getUserByEmail(@PathVariable("email") String email){
	     return userService.findUserByEmail(email);
	} 
	
	@PostMapping("/login")
	public User getUserByUsernameAndPassword(@RequestBody UserAccount userAccount) {
		return userService.getUserByUsernameAndPassword(userAccount);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Object> registerNewUser(@RequestBody User user) {
		 
		User savedUser = userService.addUser(user);
	    URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/register")
	    										  .buildAndExpand(savedUser.getId()).toUri();
	    return ResponseEntity.created(location).build();
	 }
	 
	
	@PutMapping("/{userId}")
	public ResponseEntity<?> editUser(@PathVariable Long userId, @RequestBody User user){
		
		User savedUser = userService.editUser(userId, user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/edit")
		          								  .buildAndExpand(savedUser.getId()).toUri();
		
	    return ResponseEntity.created(location).build();
	}
	
	
	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		userService.deleteUser(userId);
	 }
	
}
